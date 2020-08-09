package com.ruoyi.project.atx.service.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.ruoyi.common.utils.DateUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.atx.domain.AtxCase;
import com.ruoyi.project.atx.domain.AtxPolicy;
import com.ruoyi.project.atx.mapper.AtxPolicyMapper;
import com.ruoyi.project.atx.service.IAtxPolicyService;

/**
 * 策略信息 服务层实现
 *
 * @author wangst.fnst
 */
@Service
public class AtxPolicyServiceImpl implements IAtxPolicyService {
	@Autowired
	private AtxPolicyMapper atxPolicyMapper;

	// 文件所在的层数
	private int fileLevel;

	List<AtxCase> cases = new ArrayList<AtxCase>();

	/**
	 * 查询【请填写功能名称】
	 *
	 * @param id 【请填写功能名称】ID
	 * @return 【请填写功能名称】
	 */
	@Override
	public AtxPolicy selectAtxPolicyById(Long id)
	{
		return atxPolicyMapper.selectAtxPolicyById(id);
	}

	/**
	 * 查询【请填写功能名称】列表
	 *
	 *
	 * @return 【请填写功能名称】
	 */
	@Override
	public List<AtxPolicy> selectAtxPolicyList()
	{
		return atxPolicyMapper.selectAtxPolicyList();
	}

	/**
	 * 新增【请填写功能名称】
	 *
	 * @param atxPolicy 【请填写功能名称】
	 * @return 结果
	 */
	@Override
	public int insertAtxPolicy(AtxPolicy atxPolicy)
	{
		atxPolicy.setCreateTime(DateUtils.getNowDate());
		return atxPolicyMapper.insertAtxPolicy(atxPolicy);
	}

	/**
	 * 修改【请填写功能名称】
	 *
	 * @param atxPolicy 【请填写功能名称】
	 * @return 结果
	 */
	@Override
	public int updateAtxPolicy(AtxPolicy atxPolicy)
	{
		atxPolicy.setUpdateTime(DateUtils.getNowDate());
		return atxPolicyMapper.updateAtxPolicy(atxPolicy);
	}

	/**
	 * 批量删除【请填写功能名称】
	 *
	 * @param ids 需要删除的【请填写功能名称】ID
	 * @return 结果
	 */
	@Override
	public int deleteAtxPolicyByIds(Long[] ids)
	{
		return atxPolicyMapper.deleteAtxPolicyByIds(ids);
	}

	/**
	 * 删除【请填写功能名称】信息
	 *
	 * @param id 【请填写功能名称】ID
	 * @return 结果
	 */
	@Override
	public int deleteAtxPolicyById(Long id)
	{
		return atxPolicyMapper.deleteAtxPolicyById(id);
	}
	/**
	 * 遍历case
	 * 
	 * @return case集合
	 */
	@Override
	public List<AtxCase> getCaseList(String filepath) throws FileNotFoundException, IOException {
		String dirPath = "E:\\share\\atx";
		cases = new ArrayList<>();
		printDir(dirPath);
		readFile(dirPath);
		return cases;
	}

	/**
	 * 生成输出格式
	 * 
	 * @param name  输出的文件名或目录名
	 * @param level 输出的文件名或者目录名所在的层次
	 * @return 输出的字符串
	 */
	private String createPrintStr(String name, int level) {
		// 输出的前缀
		String printStr = "";
		// 按层次进行缩进
		for (int i = 0; i < level; i++) {
			printStr = printStr + "  ";
		}
		printStr = printStr + "- " + name;
		return printStr;
	}

	/**
	 * 输出初始给定的目录
	 * 
	 * @param dirPath 给定的目录
	 */
	private void printDir(String dirPath) {
		// 将给定的目录进行分割
		String[] dirNameList = dirPath.split("\\\\");
		// 设定文件level的base
		fileLevel = dirNameList.length;
		// 按格式输出
		for (int i = 0; i < dirNameList.length; i++) {
			System.out.println(createPrintStr(dirNameList[i], i));
		}
	}

	/**
	 * 输出给定目录下的文件，包括子目录中的文件
	 * 
	 * @param dirPath 给定的目录
	 */
	private void readFile(String dirPath) {
		// 建立当前目录中文件的File对象
		File file = new File(dirPath);
		// 取得代表目录中所有文件的File对象数组
		File[] list = file.listFiles();
		// 遍历file数组
		for (int i = 0; i < list.length; i++) {
			AtxCase caseItem = new AtxCase();
			if (list[i].isDirectory()) {
				System.out.println(createPrintStr(list[i].getName(), fileLevel) + "--fileLevel:" + fileLevel);
				fileLevel++;
				// 递归子目录
				readFile(list[i].getPath());
				fileLevel--;
				if (list[i].getPath() != null && !list[i].getPath().isEmpty()) {
					caseItem.setCaseId(list[i].getPath());
					caseItem.setCaseName(list[i].getName());
					caseItem.setParentId(list[i].getParent());
					// 把所有上层目录放进Ancestors
					String[] strs = list[i].getPath().split("\\\\");
					List<String> list1 = Arrays.asList(strs);
					List<String> parents = new ArrayList<String>(list1);
					parents.remove(parents.size() - 1);
					caseItem.setAncestors(StringUtils.join(parents, ","));

					String arr[] = new String[0];
					caseItem.setChildren(arr);
					cases.add(caseItem);
				}
			} else {
				if (list[i].getName().endsWith(".bs")) {
					caseItem.setCaseId(list[i].getPath());
					caseItem.setCaseName(list[i].getName());
					caseItem.setParentId(list[i].getParent());
					findAllParents(list[i].getParent(), caseItem);

					findGif(list[i].getParent(), caseItem);
					System.out.println(createPrintStr(list[i].getName(), fileLevel) + "--fileLevel:" + fileLevel
							+ "--path:" + list[i].getPath());
					cases.add(caseItem);
				}
			}

		}
	}

	/**
	 * 查找当前目录下的gif文件
	 * 
	 * @param dirPath 给定的目录
	 */
	private void findGif(String dirPath, AtxCase caseItem) {
		// 建立当前目录中文件的File对象
		File file = new File(dirPath);
		// 取得代表目录中所有文件的File对象数组
		File[] list = file.listFiles();
		// 遍历file数组
		for (int i = 0; i < list.length; i++) {
			if (!list[i].isDirectory() && list[i].getName().endsWith(".gif")) {
				String name = list[i].getName();
				name = name.substring(0, name.length() - 4);
				if (name.startsWith("_")) {
					caseItem.setCaseTime(name.substring(1, name.length()));
				} else {
					caseItem.setCaseTime(name);
					caseItem.setGifUrl(
							list[i].getPath().replace("E:\\share\\atx", "http://10.167.218.50:90/windowshare/atx"));
				}
			}
		}
	}

	/**
	 * 查找当前目录所有的父级目录
	 * 
	 * @param dirPath 给定的目录
	 */
	private void findAllParents(String dirPath, AtxCase caseItem) {
		String[] strs = dirPath.split("\\\\");
		List<String> arr = new ArrayList<>();
		arr.add(strs[0]);
		for (int i = 1; i < strs.length; i++) {
			arr.add(arr.get(arr.size() - 1) + "\\" + strs[i]);
		}
		caseItem.setAncestors(StringUtils.join(arr, ","));
	}
}
