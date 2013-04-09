package com.biscato.kollaidoscope.util;

import java.util.Comparator;

import com.biscato.kollaidoscope.model.Question;


public class QuestionRankComparator implements Comparator<Question>{

	@Override
	public int compare(Question q1, Question q2) {
		int q1Rank = q1.getRank();
		int q2Rank = q2.getRank();
        return (q1Rank < q2Rank) ? -1: (q1Rank > q2Rank) ? 1:0;
	}
}
