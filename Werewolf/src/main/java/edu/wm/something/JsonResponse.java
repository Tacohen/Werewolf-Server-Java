package edu.wm.something;

public class JsonResponse {

	private String status;
	private Object object;
	private Class<?> objClass;

	public JsonResponse() {}

	public JsonResponse(String status, Object object) {
		this.status = status;
		this.object = object;
		this.objClass = object.getClass();
	}

	public Class<?> getObjClass() {
		return objClass;
	}

	public void setObjClass(Class<?> objClass) {
		this.objClass = objClass;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.object = object;
	}

}

