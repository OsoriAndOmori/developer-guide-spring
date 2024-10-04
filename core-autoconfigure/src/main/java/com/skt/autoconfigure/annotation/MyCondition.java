package com.skt.autoconfigure.annotation;

import com.skt.autoconfigure.match.MyConditionMatch;

public @interface MyCondition {
    Class<? extends MyConditionMatch>[] value();
}
