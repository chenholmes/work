package com.ruoyi.project.server.attribute;

import io.netty.util.AttributeKey;

/**
 * @author chenli.fnst
 * @date 2020/8/6 10:06
 */
public interface Attributes {

    AttributeKey<String> client = AttributeKey.newInstance("client");
}
