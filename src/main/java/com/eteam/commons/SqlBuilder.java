package com.eteam.commons;

import java.util.ArrayList;
import java.util.List;

/**
 * 用来拼装sql语句所使用的对象.
 *
 */
public class SqlBuilder {
	
	private StringBuffer outSQL;
	
	private List<Object> argList;
	
	private List<Object> argTypeList;
	
	private boolean isOK;
	
	private boolean inIf;
	
	public SqlBuilder() {
		outSQL=new StringBuffer();
		init();
	}
	
	public SqlBuilder(int bufferSize) {
		outSQL=new StringBuffer(bufferSize);
		init();
	}
	
	public SqlBuilder(String ssql) {
		outSQL=new StringBuffer(ssql);
		init();
	}
	
	private void init(){
		argList=new ArrayList<Object>();
		argTypeList=new ArrayList<Object>();
		isOK=false;
		inIf=false;
	}
	
	/**
	 * 取得最终拼装成的sql语句
	 * @return sql语句
	 */
	public String getSQL(){
		return outSQL.toString();
	}

	public String toString(){
		return getSQL();
	}
	
	/**
	 * 取得sql builder内所有的sql语句的参数
	 * @return 参数组成的集合
	 */
	public Object[] getSQLArgs(){
		return argList != null ? argList.toArray() : null;
	}
	
	/**
	 * 取得sql builder内所有的sql语句参数类型的集合
	 * @return 参数类型组成的集合
	 */
	public int[] getArgTypes(){
		int[] args=new int[argTypeList.size()];
		for (int i=0;i<argTypeList.size();i++){
			args[i]=((Integer)argTypeList.get(i)).intValue();
		}
		return args;
	}
	
	/**
	 * 向sql builder中追加 sql语句片段
	 * @param ssql sql语句片段
	 * @return sql builder本身
	 */
	public SqlBuilder append(Object ssql){
		if (!inIf||isOK){
			outSQL.append(ssql);
		}
		return this;
	}

	
	/**
	 * 向sql builder中添加sql语句所需参数的参数类型
	 * @param argType sql语句的参数的类型标识. 如 Types.VARCHAR
	 * @return sql builder本身
	 */
	public SqlBuilder addType(int argType){
		if (!inIf||isOK){
			argTypeList.add(new Integer(argType));
		}
		return this;
	}
	
	/**
	 * 向sql builder中添加sql语句所需参数的参数类型
	 * @param sqlArg sql语句的参数. 用于PreparedStatement 方式
	 * @return sql builder本身
	 */
	public SqlBuilder addArg(Object sqlArg){
		if (!inIf||isOK){
			if(sqlArg==null){
				sqlArg="";
			}
			argList.add(sqlArg);
		}
		return this;
	}
	
	/**
	 * 向sql builder中添加sql语句所需参数的参数类型
	 * @param sqlArg sql语句的参数. 用于PreparedStatement 方式
	 * @return sql builder本身
	 */
	public SqlBuilder addArg(long sqlArg){
		if (!inIf||isOK){
			argList.add(new Long(sqlArg));
		}
		return this;
	}
	
	/**
	 * 向sql builder中添加sql语句所需参数的参数类型
	 * @param sqlArg sql语句的参数. 用于PreparedStatement 方式
	 * @return sql builder本身
	 */
	public SqlBuilder addArg(double sqlArg){
		if (!inIf||isOK){
			argList.add(new Double(sqlArg));
		}
		return this;
	}
	
	/**
     * 追加Like参数.
     * 返回格式为"%arg%"，其中arg中的空格也会被替换为"%".
     * @param arg 待追加的参数值
     * @return 追加参数后的SqlBuilder
     * @author 
     */
    public SqlBuilder addLikeArg(Object arg){
        String str = (String)arg;
        this.argList.add("%"+str.replaceAll(" +", "%")+"%");
        return this;
    }
}
