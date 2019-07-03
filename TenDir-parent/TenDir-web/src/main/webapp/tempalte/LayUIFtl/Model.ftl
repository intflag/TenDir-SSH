package com.intflag.tendir.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

/**
 * @功能说明：${functionComment}
 * @作者： ${author}
 * @创建日期：${date}
 * @版本号：V1.0
 */
public class ${className} implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	
	<#list cloums as c>
	private ${c.cloumsType} ${ c.columnName};//${ c.columnComment}

	</#list>

	//构造方法
	public ${className}() {
	}
	
	<#list cloums as c>
	
	public ${c.cloumsType} get${ c.UpUmnName}() {
		return  ${ c.columnName};
	}

	public void set${ c.UpUmnName}(${c.cloumsType} ${ c.columnName}) {
		this.${ c.columnName} = ${ c.columnName};
	}

	</#list>
}
