package com.newbiest.vanchip.rest.mobile;

import com.newbiest.base.msg.Response;
import lombok.Data;

@Data
public class MobileResponse extends Response {
	
	private static final long serialVersionUID = 1L;
	
	private MobileResponseBody body;
	
}
