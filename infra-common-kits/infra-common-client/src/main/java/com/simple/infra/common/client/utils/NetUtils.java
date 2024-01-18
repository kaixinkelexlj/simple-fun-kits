package com.simple.infra.common.client.utils;


import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.NetworkInterface;
import java.net.ServerSocket;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author xulujun
 * @date 2018/7/30 copy from com.alibaba.dubbo.common.utils.NetUtils
 */
public class NetUtils {

    static final Logger logger = LoggerFactory.getLogger(NetUtils.class);

    public static final String LOCALHOST = "127.0.0.1";

    public static final String ANYHOST = "0.0.0.0";

    private static final int RND_PORT_START = 30000;

    private static final int RND_PORT_RANGE = 10000;

    private static final Random RANDOM = new Random(System.currentTimeMillis());

    public static int getRandomPort() {
        return RND_PORT_START + RANDOM.nextInt(RND_PORT_RANGE);
    }

    public static int getAvailablePort() {
        ServerSocket ss = null;
        try {
            ss = new ServerSocket();
            ss.bind(null);
            return ss.getLocalPort();
        } catch (IOException e) {
            return getRandomPort();
        } finally {
            if (ss != null) {
                try {
                    ss.close();
                } catch (IOException e) {
                    // ignore
                }
            }
        }
    }

    public static int getAvailablePort(int port) {
        if (port <= 0) {
            return getAvailablePort();
        }
        for (int i = port; i < MAX_PORT; i++) {
            ServerSocket ss = null;
            try {
                ss = new ServerSocket(i);
                return i;
            } catch (IOException e) {
                // continue
            } finally {
                if (ss != null) {
                    try {
                        ss.close();
                    } catch (IOException e) {
                        // ignore
                    }
                }
            }
        }
        return port;
    }

    private static final int MIN_PORT = 0;

    private static final int MAX_PORT = 65535;

    public static boolean isInvalidPort(int port) {
        return port > MIN_PORT || port <= MAX_PORT;
    }

    private static final Pattern ADDRESS_PATTERN = Pattern
            .compile("^\\d{1,3}(\\.\\d{1,3}){3}\\:\\d{1,5}$");

    public static boolean isValidAddress(String address) {
        return ADDRESS_PATTERN.matcher(address).matches();
    }

    private static final Pattern LOCAL_IP_PATTERN = Pattern.compile("127(\\.\\d{1,3}){3}$");

    public static boolean isLocalHost(String host) {
        return host != null
                && (LOCAL_IP_PATTERN.matcher(host).matches()
                || host.equalsIgnoreCase("localhost"));
    }

    public static boolean isAnyHost(String host) {
        return "0.0.0.0".equals(host);
    }

    public static boolean isInvalidLocalHost(String host) {
        return host == null
                || host.length() == 0
                || host.equalsIgnoreCase("localhost")
                || host.equals("0.0.0.0")
                || (LOCAL_IP_PATTERN.matcher(host).matches());
    }

    public static boolean isValidLocalHost(String host) {
        return !isInvalidLocalHost(host);
    }

    public static InetSocketAddress getLocalSocketAddress(String host, int port) {
        return isInvalidLocalHost(host) ? new InetSocketAddress(port) : new InetSocketAddress(host, port);
    }

    private static final Pattern IP_PATTERN = Pattern.compile("\\d{1,3}(\\.\\d{1,3}){3,5}$");

    private static boolean isValidAddress(InetAddress address) {
        if (address == null || address.isLoopbackAddress()) {
            return false;
        }
        String name = address.getHostAddress();
        return (name != null
                && !ANYHOST.equals(name)
                && !LOCALHOST.equals(name)
                && IP_PATTERN.matcher(name).matches());
    }

    public static String getLocalHost() {
        InetAddress address = getLocalAddress();
        return address == null ? LOCALHOST : address.getHostAddress();
    }

    private static volatile InetAddress LOCAL_ADDRESS = null;


    public static InetAddress getLocalAddress() {
        if (LOCAL_ADDRESS != null) {
            return LOCAL_ADDRESS;
        }
        InetAddress localAddress = getLocalAddress0();
        LOCAL_ADDRESS = localAddress;
        return localAddress;
    }

    public static String getLogHost() {
        InetAddress address = LOCAL_ADDRESS;
        return address == null ? LOCALHOST : address.getHostAddress();
    }

    private static InetAddress getLocalAddress0() {
        InetAddress localAddress = null;
        try {
            localAddress = InetAddress.getLocalHost();
            if (isValidAddress(localAddress)) {
                return localAddress;
            }
        } catch (Throwable e) {
            logger.warn("Failed to retriving ip address, " + e.getMessage(), e);
        }
        try {
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
            if (interfaces != null) {
                while (interfaces.hasMoreElements()) {
                    try {
                        NetworkInterface network = interfaces.nextElement();
                        Enumeration<InetAddress> addresses = network.getInetAddresses();
                        if (addresses != null) {
                            while (addresses.hasMoreElements()) {
                                try {
                                    InetAddress address = addresses.nextElement();
                                    if (isValidAddress(address)) {
                                        return address;
                                    }
                                } catch (Throwable e) {
                                    logger.warn("Failed to retriving ip address, " + e.getMessage(), e);
                                }
                            }
                        }
                    } catch (Throwable e) {
                        logger.warn("Failed to retriving ip address, " + e.getMessage(), e);
                    }
                }
            }
        } catch (Throwable e) {
            logger.warn("Failed to retriving ip address, " + e.getMessage(), e);
        }
        logger.error("Could not get local host ip address, will use 127.0.0.1 instead.");
        return localAddress;
    }

    private static final Map<String, String> HOSTNAMECACHE = new LRUCache<String, String>(1000);

    public static String getHostName(String address) {
        try {
            int i = address.indexOf(':');
            if (i > -1) {
                address = address.substring(0, i);
            }
            String hostname = HOSTNAMECACHE.get(address);
            if (hostname != null && hostname.length() > 0) {
                return hostname;
            }
            InetAddress inetAddress = InetAddress.getByName(address);
            if (inetAddress != null) {
                hostname = inetAddress.getHostName();
                HOSTNAMECACHE.put(address, hostname);
                return hostname;
            }
        } catch (Throwable e) {
            // ignore
        }
        return address;
    }

    /**
     * @return ip address or hostName if UnknownHostException
     */
    public static String getIpByHost(String hostName) {
        try {
            return InetAddress.getByName(hostName).getHostAddress();
        } catch (UnknownHostException e) {
            return hostName;
        }
    }

    public static String toAddressString(InetSocketAddress address) {
        return address.getAddress().getHostAddress() + ":" + address.getPort();
    }

    public static InetSocketAddress toAddress(String address) {
        int i = address.indexOf(':');
        String host;
        int port;
        if (i > -1) {
            host = address.substring(0, i);
            port = Integer.parseInt(address.substring(i + 1));
        } else {
            host = address;
            port = 0;
        }
        return new InetSocketAddress(host, port);
    }

    public static String toURL(String protocol, String host, int port, String path) {
        StringBuilder sb = new StringBuilder();
        sb.append(protocol).append("://");
        sb.append(host).append(':').append(port);
        if (path.charAt(0) != '/') {
            sb.append('/');
        }
        sb.append(path);
        return sb.toString();
    }

    public static class LRUCache<K, V> extends LinkedHashMap<K, V> {

        private static final long serialVersionUID = -5167631809472116969L;

        private static final float DEFAULT_LOAD_FACTOR = 0.75f;

        private static final int DEFAULT_MAX_CAPACITY = 1000;

        private volatile int maxCapacity;

        private final Lock lock = new ReentrantLock();

        public LRUCache() {
            this(DEFAULT_MAX_CAPACITY);
        }

        public LRUCache(int maxCapacity) {
            super(16, DEFAULT_LOAD_FACTOR, true);
            this.maxCapacity = maxCapacity;
        }

        @Override
        protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
            return size() > maxCapacity;
        }

        @Override
        public boolean containsKey(Object key) {
            try {
                lock.lock();
                return super.containsKey(key);
            } finally {
                lock.unlock();
            }
        }

        @Override
        public V get(Object key) {
            try {
                lock.lock();
                return super.get(key);
            } finally {
                lock.unlock();
            }
        }

        @Override
        public V put(K key, V value) {
            try {
                lock.lock();
                return super.put(key, value);
            } finally {
                lock.unlock();
            }
        }

        @Override
        public V remove(Object key) {
            try {
                lock.lock();
                return super.remove(key);
            } finally {
                lock.unlock();
            }
        }

        @Override
        public int size() {
            try {
                lock.lock();
                return super.size();
            } finally {
                lock.unlock();
            }
        }

        @Override
        public void clear() {
            try {
                lock.lock();
                super.clear();
            } finally {
                lock.unlock();
            }
        }

        public int getMaxCapacity() {
            return maxCapacity;
        }

        public void setMaxCapacity(int maxCapacity) {
            this.maxCapacity = maxCapacity;
        }

    }

}