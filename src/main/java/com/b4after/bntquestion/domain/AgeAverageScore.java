package com.b4after.bntquestion.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum AgeAverageScore {
    AGED_GROUP1(12.37, 55, 64),
    AGED_GROUP2(11.17, 65, 74),
    AGED_GROUP3(10.50, 75, 84),
    AGED_GROUP4(6.64, 85, Integer.MAX_VALUE);
    private final double average;
    private final int minAge;
    private final int maxAge;

    public static double getAverageScore(int age) {
        AgeAverageScore ageAverage = Arrays.stream(AgeAverageScore.values())
                .filter(range -> age >= range.minAge && age <= range.maxAge)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("나이가 평균 점수를 구할 수 있는 범위 밖입니다."));
        return ageAverage.getAverage();
    }
}
