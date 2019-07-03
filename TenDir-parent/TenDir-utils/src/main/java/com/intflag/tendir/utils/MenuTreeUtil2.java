package com.intflag.tendir.utils;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.intflag.tendir.entity.Resource;


public class MenuTreeUtil2 {
	   
	/**
	 * 建立树菜单
	 * @param menusDB 菜单集合（不是树）
	 * @return 有样式的树的html字符串
	 */
    public static String buildTreeHtml(List<Resource> menusDB){  
    	StringBuffer html = new StringBuffer();
        for (int i=0;i<menusDB.size();i++) { 
        	Resource node=menusDB.get(i);
            if (node.getParentId() == null) { 
            	boolean childrenHas=false;
            	List<Resource> children = getChildren(menusDB,node);  
            	if(!children.isEmpty()) {
            		childrenHas=true;
            	}
            	
            	//------------------------------------------------------------------------
            	if (StringUtils.isNotBlank(node.getResurl())) {
            		html.append("<li class='layui-nav-item layui-this'><a class='layui-nav-item cy-page' href='javascript:;' data-url='"+node.getResurl()+"' title='"+node.getResname()+"'><i class='"+node.getIcon()+"'></i> "+node.getResname()+"</a>");
            	} else {
            		html.append("<li class='layui-nav-item'><a class='' href='javascript:;' title='"+node.getResname()+"'><i class='"+node.getIcon()+"'></i>"+node.getResname()+"<span class='layui-nav-more'></span></a>");
            	}
            	
            	build(menusDB,node,html);  
            	
            	html.append("</li>");
            }  
        }  
        return html.toString();  
    }  
      
    private static void build(List<Resource> menusDB,Resource node,StringBuffer html){  
        List<Resource> children = getChildren(menusDB,node); 
        if (!children.isEmpty()) {  
            html.append("<dl class='layui-nav-child'>");
            for (Resource child : children) {  
            	boolean childrenHas=false;
            	List<Resource> children2 = getChildren(menusDB,child);
            	if(!children2.isEmpty()) {
            		childrenHas=true;
            	}
            	html.append("<dd><a class='cy-page' href='javascript:;' data-url='"+child.getResurl()+"' title='"+child.getResname()+"'>"+child.getResname()+"</a>");
            	build(menusDB,child,html);  
            	html.append("</dd>");
            } 
            html.append("</dl>");
        }   
    }  
      
    private static List<Resource> getChildren(List<Resource> menusDB,Resource node){  
        List<Resource> children = new ArrayList<Resource>();  
        String id = node.getResourceId();  
        for (Resource child : menusDB) {  
            if (id.equals(child.getParentId())) {  
                children.add(child);               
            }  
        }  
        return children;  
    }  
    
	   
	/**
	 * 建立树菜单
	 * @param menusDB 菜单集合（不是树）
	 * @return 有样式的树的菜单集合
	 */
    public static List<Resource> buildTree(List<Resource> menusDB){  
    	List<Resource> res=new ArrayList<Resource>();
    	  for (Resource node:menusDB) { 
    		  if ("0".equals(node.getParentId())) { 
    			  List<Resource> children = getChildren(menusDB,node);  
    			  node.getChildren().add(children);
    			  build(menusDB,node,res); 
    			  res.add(node);
    		  }    		
    	  }
    	return res;
    }  
    
    private static void build(List<Resource> menusDB,Resource node,List<Resource> res){  
        List<Resource> children = getChildren(menusDB,node); 
        if (!children.isEmpty()) {  
            for (Resource child : children) {  
            	List<Resource> children2 = getChildren(menusDB,child);
            	child.getChildren().add(children);
                build(menusDB,child,res);  
            } 
        }   
    } 
    
    
}
