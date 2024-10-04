package com.skt.autoconfigure.match;

public class AlwaysMatchTrueCondition implements MyConditionMatch {
    @Override
    public boolean match() {
        return true;
    }
}
