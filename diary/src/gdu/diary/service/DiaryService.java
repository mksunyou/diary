package gdu.diary.service;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class DiaryService {
	public Map<String, Object> getDiary(String targetYear, String targetMonth) { //많은 return값이 나오기 때문에 vo타입을 만들어야함. 따라서 Map으로 만듬.
		// 타겟 년, 월, 일(날짜)
		// 타겟의 1일(날짜)
		// 타겟의 마지막 일의 숫자 -> endDay
		
		// startBlank + endDay + 7 - (startBlank + endDay)%7
		// 전체 셀의 개수(마지막일의 숫자보다는 크고 7로 나누어 떨어져야한다.
		// 앞의 빈셀 개수 -> startBlank
		// 이번달 숫자가 나와야 할 셀의 개수(1~마지막날짜)
		// 뒤의 빈 셀의 개수 -> endBlank -> 7 - (startBlank + endDay)%7
		Map<String, Object> map = new HashMap<String, Object>();
		Calendar target = Calendar.getInstance();
		
		
		if(targetYear != null) {
			target.set(Calendar.YEAR, Integer.parseInt(targetYear));
		}
		if(targetMonth != null) { 
			//13월이 되거나 0이 되면 내년과 전년으로 자동으로 Year가 변함.( ex)21년 12월에서 다음을 누르면 자동으로 22년 1월로 바꿔줌)
			target.set(Calendar.MONTH, Integer.parseInt(targetMonth));
		}
		/*
		int numTargetMonth = 0;
		int numTargetYear = 0;
		
		if(targetMonth != null && targetYear != null) {
			numTargetYear = Integer.parseInt(targetYear);
			numTargetMonth = Integer.parseInt(targetMonth);
			if(numTargetMonth == 0) {
				numTargetYear-=1;
				numTargetMonth=12;
			} else if(numTargetMonth == 13) {
				numTargetYear+=1;
				numTargetMonth=1;
			}
			target.set(Calendar.YEAR, numTargetYear);
			target.set(Calendar.MONTH, numTargetMonth-1);
		}
		*/
		
		target.set(Calendar.DATE, 1);
		// 1숫자 앞에 와야할 빈 셀의 개수
		int startBlank = target.get(Calendar.DAY_OF_WEEK)-1;

		// 마지막 날짜
		int endDay = target.getActualMaximum(Calendar.DATE);
		
		// 뒤의 빈셀
		int endBlank = 0;
		if((startBlank + endDay) % 7 != 0) {
			endBlank = 7-(startBlank + endDay) % 7;
		}
		
		// 모든 Cell의 갯수
		//int totalCell = startBlank + endDay +  endBlank;
		
		//return할 값
		map.put("targetYear", target.get(Calendar.YEAR));
		map.put("targetMonth", target.get(Calendar.MONTH));
		map.put("startBlank", startBlank);
		map.put("endDay", endDay);
		map.put("endBlank", endBlank);
		
		return map;
	}
}
