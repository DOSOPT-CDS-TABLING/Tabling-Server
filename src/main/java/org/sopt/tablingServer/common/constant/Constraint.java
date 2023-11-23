package org.sopt.tablingServer.common.constant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Constraint {

    public static final int MAX_PERSON_COUNT = 99;
    public static final int MAX_BEFORE_COUNT = 20;
    public static final int MAX_PRICE_STEP = 20;

    public static final int REVIEW_DEADLINE_DAYS = 4;
    public static final int MINUTES_IN_A_DAY = 1440;
}
