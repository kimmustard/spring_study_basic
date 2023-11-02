package hello.advanced.trace;

public class TraceStatus {

	
	private TraceId traceId;	// 내부 트랜잭션 ID와 level을 가지고 있다.
	private Long startTimeMs;	// 로그 시작시간. 로그종료시 이 시작 시간을 기준으로 시작~종료까지 전체 수행 시간을 구할 수 있다.
	private String message;	// 시작시 사용한 메세지이다. 이후 로그 종료시에도 이 메시지를 사용해서 출력한다.
	
	public TraceStatus(TraceId traceId, Long startTimeMs, String message) {
		this.traceId = traceId;
		this.startTimeMs = startTimeMs;
		this.message = message;
	}

	public TraceId getTraceId() {
		return traceId;
	}

	public void setTraceId(TraceId traceId) {
		this.traceId = traceId;
	}

	public Long getStartTimeMs() {
		return startTimeMs;
	}

	public void setStartTimeMs(Long startTimeMs) {
		this.startTimeMs = startTimeMs;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
	
}
