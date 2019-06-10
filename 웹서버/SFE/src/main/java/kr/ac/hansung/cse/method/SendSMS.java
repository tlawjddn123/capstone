package kr.ac.hansung.cse.method;

import java.util.HashMap;

import org.json.simple.JSONObject;

public class SendSMS {
	public void sendSMS() {	
		/*
		 * 서버에서 받은 API_KEY, API_SECRET를 입력해주세요.
		 */
		String api_key = "NCSXM3PMCPZ4GGOF";
		String api_secret = "VWOOD3BXNCIOCWG87VY1IE1D45FDTN7E";
		Coolsms coolsms = new Coolsms(api_key, api_secret);
	
		
		HashMap<String, String> set = new HashMap<String, String>();
		set.put("to", "01056223115"); // 수신번호

		// 10월 16일 이후로 발신번호 사전등록제로 인해 등록된 발신번호로만 문자를 보내실 수 있습니다.
		set.put("from", "01064252840"); // 발신번호
		set.put("text", "화재신고!\n장소: 한성대학교 미래관 DLC\n(지하2층)\n출동바랍니다."); // 문자내용
		set.put("type", "sms"); // 문자 타입
		set.put("charset", "utf8"); // 인코딩 방식
		
		/*set.put("to", "01000000000, 01000000001"); // 받는사람 번호 여러개 입력시
	
		set.put("country", "KR"); // 국가코드 한국:KR 일본:JP 미국:US 중국:CN
		
		
		
		
		set.put("srk", ""); // 솔루션 제공 수수료를 정산받을 솔루션 등록키
		set.put("mode", "test"); // test모드 수신번호를 반드시 01000000000 으로 테스트하세요. 예약필드 datetime는 무시됨. 결과값은 60. 잔액에서 실제 차감되며 다음날 새벽에 재충전됨
		set.put("app_version", ""); // 어플리케이션 버젼 예) Purplebook 4.1
		set.put("datetime", "201701151230"); // 예약전송시 날짜 설정		
		 */		

		JSONObject result = coolsms.send(set); // 보내기&전송결과받기
		if ((Boolean) result.get("status") == true) {
			// 메시지 보내기 성공 및 전송결과 출력
			System.out.println("성공");			
		
		} else {
			// 메시지 보내기 실패
			System.out.println("실패");
			
		}		
	}	
}
