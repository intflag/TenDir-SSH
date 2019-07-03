<?xml version="1.0" encoding="utf-8"?>
<!DOCTYPE hibernate-mapping PUBLIC "-//Hibernate/Hibernate Mapping DTD 3.0//EN"
"http://hibernate.sourceforge.net/hibernate-mapping-3.0.dtd">
<hibernate-mapping>
    <class name="com.intflag.tendir.entity.${className}" table="${Table_NAME}" >
        <id name="${entityId}" type="java.lang.String">
            <column name="${tableId}" length="32" />
            <generator class="uuid" />
        </id>
        
        <#list cloums as c>
	        <property name="${c.columnName}" />
        </#list>
    </class>
</hibernate-mapping>
