package com.intflag.tendir.utils;

import java.util.ArrayList;
import java.util.List;

import com.intflag.tendir.entity.Resource;

public class MenuTreeUtil {
	/**
	 * 查找所有根节点
	 * 
	 * @param nodes
	 * @return
	 */
	public static List<Resource> findRoot(List<Resource> nodes) {
		List<Resource> list = new ArrayList<>();
		for (Resource resource : nodes) {
			if (resource.getParentResource() == null) {
				list.add(resource);
			}
		}
		return list;
	}

	/**
	 * 得到树
	 * @param nodes
	 * @return
	 */
	public static List<Resource> GetTree(List<Resource> nodes) {
		List<Resource> root = findRoot(nodes);
		return BuildTree(nodes, root);
	}

	/**
	 * 创建树
	 * @param nodes
	 * @param root
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Resource> BuildTree(List<Resource> nodes, List<Resource> root) {
		for (Resource resource : root) {
			List<Resource> children = findChildren(resource, nodes);
			resource.getChildren().clear();
			resource.getChildren().addAll(children);
		}
		return root;
	}

	/**
	 * 查找所有子节点
	 * @param resource
	 * @param nodes
	 * @return
	 */
	public static List<Resource> findChildren(Resource resource, List<Resource> nodes) {
		List<Resource> list = new ArrayList<>();
		for (Resource res : nodes) {
			if (res.getParentResource() != null && res.getParentResource().getResourceId().equals(resource.getResourceId())) {
				list.add(res);
			}
		}
		return list;
	}

}
