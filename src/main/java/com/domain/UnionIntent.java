package com.domain;

import java.util.ArrayList;
import java.util.List;

public class UnionIntent {
    private List<UserIntent> userIntents;

    private List<Integer> unions;// 1 = UNION, 2 = UNION ALL

    public UnionIntent() {
        userIntents = new ArrayList<>();
        unions = new ArrayList<>();
    }

    public List<UserIntent> getUserIntents() {
        return userIntents;
    }

    public void setUserIntents(List<UserIntent> userIntents) {
        this.userIntents = userIntents;
    }

    public List<Integer> getUnions() {
        return unions;
    }

    public void setUnions(List<Integer> unions) {
        this.unions = unions;
    }
}
