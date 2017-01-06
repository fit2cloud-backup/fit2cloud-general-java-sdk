package com.fit2cloud.sdk.model;

public class ServiceCatalogOrderStatus {
	public static final String PENDING = "pending";
	public static final String APPROVED = "approved";
	public static final String REJECTED = "rejected";
	public static final String PROCESSING = "processing";
	public static final String BUILDING = "building";
	public static final String SUCCESS = "success";
	public static final String FAILED = "failed";
	public static final String EXPIRED_HANDLE_SUCCESS = "expired_handle_success";
	public static final String EXPIRED_HANDLE_FAILED = "expired_handle_failed";

	public static boolean isValidStatus(String status) {
		if (status == null) {
			return false;
		}
		if (status.equals(PENDING) || status.equals(APPROVED) || status.equals(REJECTED) || status.equals(PROCESSING)
				|| status.equals(BUILDING) || status.equals(SUCCESS) || status.equals(FAILED)
				|| status.equals(EXPIRED_HANDLE_SUCCESS) || status.equals(EXPIRED_HANDLE_FAILED)) {
			return true;
		}
		return false;
	}
}
