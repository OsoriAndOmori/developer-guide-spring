package com.skt.autoconfigure.match;

public class AlwaysMatchFalseCondition implements MyConditionMatch {
    @Override
    public boolean match() {
        return false;
    }
}
