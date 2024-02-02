package com.simple.infra.common.client.utils;

import org.junit.Test;

/**
 * @author xulujun@kuaishou.com
 * Created on 2024-01-18
 */
public class NetUtilsTest extends TestUtils {

  @Test
  public void testGetLocalHost() throws Exception {
    String ip = NetUtils.getLocalHost();
    test(ip, ip != null);
  }

}