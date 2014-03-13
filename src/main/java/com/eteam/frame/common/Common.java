package com.eteam.frame.common;

import org.dom4j.DocumentException;

public class Common {

	/*public static HashMap<String, String> constant = new HashMap<String, String>();

	static {
		InputStream in = null;
		try {
			Properties props = new Properties();
			in = Common.class.getResourceAsStream("/constant.properties");
			props.load(in);
			Iterator<Entry<Object, Object>> it = props.entrySet().iterator();
			while (it.hasNext()) {
				Entry<Object, Object> entry = it.next();
				String key = (String) entry.getKey();
				String value = (String) entry.getValue();
				constant.put(key, value);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (in != null)
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	}

	public static boolean isEmpty(String... str) {
		boolean result = false;
		if (str.length == 0) {
			return true;
		} else {
			for (String temp : str) {
				if (temp == null || "".equals(temp.trim())) {
					result = true;
					break;
				}
			}
		}
		return result;
	}

	public static String getValueFromProperties(String key) {

		return constant.get(key);
	}

	*//**
	 * 从properties中获得的中文
	 * 
	 * @param content
	 *            从properties中获得的中文
	 * @param oldCharSet
	 *            旧的编码格式 iso-8859-1
	 * @param newCharSet
	 *            新的编码格式 utf-8
	 * @return
	 *//*
	public static String getChineseFromProperties(String content,
			String oldCharSet, String newCharSet) {

		if (content != null && !"".equals(content.trim())) {
			String tempContent;
			try {
				tempContent = new String(content.getBytes(oldCharSet),
						newCharSet);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
				tempContent = content;
			}
			return tempContent;
		} else {
			return content;
		}
	}

	*//**
	 * 把值按配置文件变成简单的无重复的xml,
	 * 
	 * @param xml
	 * @param names
	 * @return
	 * @throws DocumentException
	 *//*
	public static String changeToXml(String xmlName,
			HashMap<String, String> values) {

		StringBuffer result = new StringBuffer();

		result.append(Common.getValueFromProperties(xmlName + "_@begin"));
		String rootTemp = xmlName + "_@root";
		String root = Common.getValueFromProperties(rootTemp);

		Common.doLoopForChangeToXml(result, rootTemp, root, values);
		return result.toString();
	}

	public static void doLoopForChangeToXml(StringBuffer result,
			String rootTemp, String root, HashMap<String, String> values) {

		result.append("<");
		result.append(root);
		result.append(">");
		String other = null;

		other = Common.getValueFromProperties(rootTemp + "_" + root);
		if (Common.isEmpty(other)) {
			// 看看有没有值，然后放进去
			String tempValue = values.get(root);
			if (!Common.isEmpty(tempValue)) {
				result.append(tempValue);
			}
		} else {
			String[] names = other.split(",");
			// 再做一遍
			for (String tempName : names) {
				doLoopForChangeToXml(result, rootTemp, tempName, values);
			}
		}
		result.append("</");
		result.append(root);
		result.append(">");

	}

	*//**
	 * 把xml变成list
	 * 
	 * @param xml
	 * @param names
	 * @return
	 * @throws DocumentException
	 *//*
	public static void changeXmlToCxList(String hiddenNumber, String xml,
			ResultVO tempResult) {

		tempResult.setEmpty();
		List<DwData> list = new ArrayList<DwData>();
		try {

			Document dom = DocumentHelper.parseText(xml);
			Element objects = dom.getRootElement();
			Iterator iterForEnvelope = objects.elementIterator("ENVELOPE");
			Element object = null;
			Element enterprise = null;
			String datasize = null;
			while (iterForEnvelope.hasNext()) {
				object = (Element) iterForEnvelope.next();
				String recode = object.elementTextTrim("RECODE");
				String message = object.elementTextTrim("MESSAGE");
				datasize = object.elementTextTrim("DATASIZE");
				tempResult.getResult().put("recode", recode);
				tempResult.getResult().put("message", message);
				tempResult.getResult().put("datasize", datasize);
			}
			if (!"01".equals(tempResult.getResult().get("recode"))) {
				tempResult.setCheckResultAndErrorInfo("3",
						"后台异常,code:Common-01");
			} else {
				// (需注意日期格式***********)
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				Date now = new Date();

				Iterator iterForDatainfo = objects.elementIterator("DATAINFO");
				Iterator iterForEnterprise = null;
				if ("1".equals(hiddenNumber)) {
					if (!"0".equals(datasize)) {
						while (iterForDatainfo.hasNext()) {
							object = (Element) iterForDatainfo.next();
							iterForEnterprise = object
									.elementIterator("ENTERPRISE");
							while (iterForEnterprise.hasNext()) {
								DwData tempDate = new DwData();
								enterprise = (Element) iterForEnterprise.next();
								// 机构代码
								String jgdm = enterprise
										.elementTextTrim("JGDM");
								// 机构名称
								String jgmc = enterprise
										.elementTextTrim("JGMC");
								// 经济行业
								String jjhy = enterprise
										.elementTextTrim("JJHY");
								// 经济类型
								String jjlx = enterprise
										.elementTextTrim("JJLX");
								// 机构类型
								String jglx = enterprise
										.elementTextTrim("JGLX");
								// 行政区划
								String xzqh = enterprise
										.elementTextTrim("XZQH");
								// 机构地址
								String jgdz = enterprise
										.elementTextTrim("JGDZ");
								// 办证日期
								String bzrq = enterprise
										.elementTextTrim("BZRQ");
								// 作废日期
								String zfrq = enterprise
										.elementTextTrim("ZFRQ");
								// 机构登记证号(注册号)//jgdjzh
								String zch = enterprise.elementTextTrim("ZCH");
								// 颁发单位
								String pzjgmc = enterprise
										.elementTextTrim("BZJGDM");
								// 质疑标识
								String zybz = enterprise
										.elementTextTrim("ZYBZ");
								// 是否有代码证书
								String dmzs = "true";
								if (Common.isEmpty(bzrq, zfrq, zybz)) {
									System.out.println("返回值有问题");
								} else {

									if (now.compareTo(df.parse(bzrq)) < 0) {
										dmzs = "false";
									} else {
										if (now.compareTo(df.parse(zfrq)) > 0) {
											dmzs = "false";
										} else {
											if (zybz != null
													&& "fz".equals(zybz
															.toLowerCase())) {// 忘了......
												dmzs = "false";
											}
										}

									}

								}

								tempDate.setCodeId(jgdm);// ****************
								tempDate.setOrgName(jgmc);
								tempDate.setOrgType(jglx);
								tempDate.setAreaId(xzqh);
								tempDate.setOrgAddress(jgdz);
								tempDate.setBeginTime(bzrq);
								tempDate.setEndTime(zfrq);
								tempDate.setRegistrationMark(zch);
								tempDate.setAwardedUnit(pzjgmc);
								tempDate.setHaveCertificate(dmzs);
								// ****************
								list.add(tempDate);
							}

						}
					}

				} else if ("2".equals(hiddenNumber)) {

					if (!"0".equals(datasize)) {
						while (iterForDatainfo.hasNext()) {
							object = (Element) iterForDatainfo.next();
							iterForEnterprise = object
									.elementIterator("ENTERPRISE");
							while (iterForEnterprise.hasNext()) {
								DwData tempDate = new DwData();
								enterprise = (Element) iterForEnterprise.next();
								// 机构代码
								String jgdm = enterprise
										.elementTextTrim("JGDM");
								// 机构名称
								String jgmc = enterprise
										.elementTextTrim("JGMC");
								// 经济行业
								String jjhy = enterprise
										.elementTextTrim("JJHY");
								// 经济类型
								String jjlx = enterprise
										.elementTextTrim("JJLX");
								// 机构类型
								String jglx = enterprise
										.elementTextTrim("JGLX");
								// 行政区划
								String xzqh = enterprise
										.elementTextTrim("XZQH");
								// 机构地址
								String jgdz = enterprise
										.elementTextTrim("JGDZ");
								// 办证日期
								String bzrq = enterprise
										.elementTextTrim("BZRQ");
								// 作废日期
								String zfrq = enterprise
										.elementTextTrim("ZFRQ");
								// 机构登记证号(注册号)//jgdjzh
								String zch = enterprise.elementTextTrim("ZCH");
								// 颁发单位
								String pzjgmc = enterprise
										.elementTextTrim("BZJGDM");
								// 质疑标识
								String zybz = enterprise
										.elementTextTrim("ZYBZ");
								// 是否有代码证书
								String dmzs = "true";
								if (Common.isEmpty(bzrq, zfrq, zybz)) {
									System.out.println("返回值有问题");
								} else {

									if (now.compareTo(df.parse(bzrq)) < 0) {
										dmzs = "false";
									} else {
										if (now.compareTo(df.parse(zfrq)) > 0) {
											dmzs = "false";
										} else {
											if (zybz != null
													&& "fz".equals(zybz
															.toLowerCase())) {// 忘了......
												dmzs = "false";
											}
										}

									}

								}

								tempDate.setCodeId(jgdm);// ****************
								tempDate.setOrgName(jgmc);
								tempDate.setOrgType(jglx);
								tempDate.setAreaId(xzqh);
								tempDate.setOrgAddress(jgdz);
								tempDate.setBeginTime(bzrq);
								tempDate.setEndTime(zfrq);
								tempDate.setRegistrationMark(zch);
								tempDate.setAwardedUnit(pzjgmc);
								tempDate.setHaveCertificate(dmzs);
								// ****************
								list.add(tempDate);
							}

						}
					}

				} else if ("3".equals(hiddenNumber)) {

					if (!"0".equals(datasize)) {
						while (iterForDatainfo.hasNext()) {
							object = (Element) iterForDatainfo.next();
							iterForEnterprise = object
									.elementIterator("ENTERPRISE");
							while (iterForEnterprise.hasNext()) {
								DwData tempDate = new DwData();
								enterprise = (Element) iterForEnterprise.next();
								// 机构代码
								String jgdm = enterprise
										.elementTextTrim("JGDM");
								// 机构名称
								String jgmc = enterprise
										.elementTextTrim("JGMC");
								// 经济行业
								String jjhy = enterprise
										.elementTextTrim("JJHY");
								// 经济类型
								String jjlx = enterprise
										.elementTextTrim("JJLX");
								// 机构类型
								String jglx = enterprise
										.elementTextTrim("JGLX");
								// 行政区划
								String xzqh = enterprise
										.elementTextTrim("XZQH");
								// 机构地址
								String jgdz = enterprise
										.elementTextTrim("JGDZ");
								// 办证日期
								String bzrq = enterprise
										.elementTextTrim("BZRQ");
								// 作废日期
								String zfrq = enterprise
										.elementTextTrim("ZFRQ");
								// 机构登记证号(注册号)//jgdjzh
								String zch = enterprise.elementTextTrim("ZCH");
								// 颁发单位
								String pzjgmc = enterprise
										.elementTextTrim("BZJGDM");
								// 质疑标识
								String zybz = enterprise
										.elementTextTrim("ZYBZ");
								// 是否有代码证书
								String dmzs = "true";
								if (Common.isEmpty(bzrq, zfrq, zybz)) {
									System.out.println("返回值有问题");
								} else {

									if (now.compareTo(df.parse(bzrq)) < 0) {
										dmzs = "false";
									} else {
										if (now.compareTo(df.parse(zfrq)) > 0) {
											dmzs = "false";
										} else {
											if (zybz != null
													&& "fz".equals(zybz
															.toLowerCase())) {// 忘了......
												dmzs = "false";
											}
										}

									}

								}

								tempDate.setCodeId(jgdm);// ****************
								tempDate.setOrgName(jgmc);
								tempDate.setOrgType(jglx);
								tempDate.setAreaId(xzqh);
								tempDate.setOrgAddress(jgdz);
								tempDate.setBeginTime(bzrq);
								tempDate.setEndTime(zfrq);
								tempDate.setRegistrationMark(zch);
								tempDate.setAwardedUnit(pzjgmc);
								tempDate.setHaveCertificate(dmzs);
								// ****************
								list.add(tempDate);
							}

						}
					}

				} else if ("4".equals(hiddenNumber)) {

					if (!"0".equals(datasize)) {
						while (iterForDatainfo.hasNext()) {
							object = (Element) iterForDatainfo.next();
							iterForEnterprise = object
									.elementIterator("ENTERPRISE");
							while (iterForEnterprise.hasNext()) {
								DwData tempDate = new DwData();
								enterprise = (Element) iterForEnterprise.next();
								// 机构代码
								String jgdm = enterprise
										.elementTextTrim("JGDM");
								// 机构名称
								String jgmc = enterprise
										.elementTextTrim("JGMC");
								// 经济行业
								String jjhy = enterprise
										.elementTextTrim("JJHY");
								// 经济类型
								String jjlx = enterprise
										.elementTextTrim("JJLX");
								// 机构类型
								String jglx = enterprise
										.elementTextTrim("JGLX");
								// 行政区划
								String xzqh = enterprise
										.elementTextTrim("XZQH");
								// 机构地址
								String jgdz = enterprise
										.elementTextTrim("JGDZ");
								// 办证日期
								String bzrq = enterprise
										.elementTextTrim("BZRQ");
								// 作废日期
								String zfrq = enterprise
										.elementTextTrim("ZFRQ");
								// 机构登记证号(注册号)//jgdjzh
								String zch = enterprise.elementTextTrim("ZCH");
								// 颁发单位
								String pzjgmc = enterprise
										.elementTextTrim("BZJGDM");
								// 质疑标识
								String zybz = enterprise
										.elementTextTrim("ZYBZ");
								// 是否有代码证书
								String dmzs = "true";
								if (Common.isEmpty(bzrq, zfrq, zybz)) {
									System.out.println("返回值有问题");
								} else {

									if (now.compareTo(df.parse(bzrq)) < 0) {
										dmzs = "false";
									} else {
										if (now.compareTo(df.parse(zfrq)) > 0) {
											dmzs = "false";
										} else {
											if (zybz != null
													&& "fz".equals(zybz
															.toLowerCase())) {// 忘了......
												dmzs = "false";
											}
										}

									}

								}

								tempDate.setCodeId(jgdm);// ****************
								tempDate.setOrgName(jgmc);
								tempDate.setOrgType(jglx);
								tempDate.setAreaId(xzqh);
								tempDate.setOrgAddress(jgdz);
								tempDate.setBeginTime(bzrq);
								tempDate.setEndTime(zfrq);
								tempDate.setRegistrationMark(zch);
								tempDate.setAwardedUnit(pzjgmc);
								tempDate.setHaveCertificate(dmzs);
								// ****************
								list.add(tempDate);
							}

						}
					}

				}

				tempResult.getResult().put("list", list);
				tempResult.setCheckResult("1");
			}
		} catch (Exception e) {
			e.printStackTrace();
			tempResult.setCheckResultAndErrorInfo("3", "后台异常,code:Common-02");
		}
	}

	*//**
	 * 把xml变成list
	 * 
	 * @param xml
	 * @param names
	 * @return
	 * @throws DocumentException
	 *//*
	public static void changeXmlToDwList(String hiddenNumber, String tempUuid,
			String xml, ResultVO tempResult) {

		tempResult.setEmpty();
		List<DwData> list = new ArrayList<DwData>();
		try {

			Document dom = DocumentHelper.parseText(xml);
			Element objects = dom.getRootElement();
			Iterator iterForEnvelope = objects.elementIterator("ENVELOPE");
			Element object = null;
			Element enterprise = null;

			String datasize = null;
			while (iterForEnvelope.hasNext()) {
				object = (Element) iterForEnvelope.next();
				String recode = object.elementTextTrim("RECODE");
				String message = object.elementTextTrim("MESSAGE");
				datasize = object.elementTextTrim("DATASIZE");
				tempResult.getResult().put("recode", recode);
				tempResult.getResult().put("message", message);
				tempResult.getResult().put("datasize", datasize);
			}
			if (!"01".equals(tempResult.getResult().get("recode"))) {
				tempResult.setCheckResultAndErrorInfo("3",
						"后台异常,code:Common-03");
			} else {
				Iterator iterForDatainfo = objects.elementIterator("DATAINFO");
				Iterator iterForEnterprise = null;
				if ("1".equals(hiddenNumber)) {

					if (!"0".equals(datasize)) {
						while (iterForDatainfo.hasNext()) {
							object = (Element) iterForDatainfo.next();
							iterForEnterprise = object
									.elementIterator("ENTERPRISE");
							while (iterForEnterprise.hasNext()) {
								DwData tempDate = new DwData();
								enterprise = (Element) iterForEnterprise.next();
								// 机构代码
								String jgdm = enterprise
										.elementTextTrim("JGDM");
								// 机构名称
								String jgmc = enterprise
										.elementTextTrim("JGMC");
								// 经济行业
								String jjhy = enterprise
										.elementTextTrim("JJHY");
								// 经济类型
								String jjlx = enterprise
										.elementTextTrim("JJLX");
								// 机构类型
								String jglx = enterprise
										.elementTextTrim("JGLX");
								// 行政区划
								String xzqh = enterprise
										.elementTextTrim("XZQH");
								// 机构地址
								String jgdz = enterprise
										.elementTextTrim("JGDZ");
								// 办证日期
								String bzrq = enterprise
										.elementTextTrim("BZRQ");
								// 作废日期
								String zfrq = enterprise
										.elementTextTrim("ZFRQ");
								// 机构登记证号(注册号)//jgdjzh
								String zch = enterprise.elementTextTrim("ZCH");
								// 颁发单位
								String pzjgmc = enterprise
										.elementTextTrim("BZJGDM");
								// ****************
								tempDate.setSameId(jgdm);
								tempDate.setCodeId(jgdm);
								tempDate.setOrgName(jgmc);
								tempDate.setOrgType(jglx);
								tempDate.setAreaId(xzqh);
								tempDate.setOrgAddress(jgdz);
								tempDate.setBeginTime(bzrq);
								tempDate.setEndTime(zfrq);
								tempDate.setRegistrationMark(zch);
								tempDate.setAwardedUnit(pzjgmc);
								tempDate.setTempUuid(tempUuid);
								// ****************
								list.add(tempDate);
							}

						}
					}

				} else if ("2".equals(hiddenNumber)) {

					if (!"0".equals(datasize)) {
						while (iterForDatainfo.hasNext()) {
							object = (Element) iterForDatainfo.next();
							iterForEnterprise = object
									.elementIterator("ENTERPRISE");
							while (iterForEnterprise.hasNext()) {
								DwData tempDate = new DwData();
								enterprise = (Element) iterForEnterprise.next();
								// 机构代码
								String jgdm = enterprise
										.elementTextTrim("JGDM");
								// 机构名称
								String jgmc = enterprise
										.elementTextTrim("JGMC");
								// 经济行业
								String jjhy = enterprise
										.elementTextTrim("JJHY");
								// 经济类型
								String jjlx = enterprise
										.elementTextTrim("JJLX");
								// 机构类型
								String jglx = enterprise
										.elementTextTrim("JGLX");
								// 行政区划
								String xzqh = enterprise
										.elementTextTrim("XZQH");
								// 机构地址
								String jgdz = enterprise
										.elementTextTrim("JGDZ");
								// 办证日期
								String bzrq = enterprise
										.elementTextTrim("BZRQ");
								// 作废日期
								String zfrq = enterprise
										.elementTextTrim("ZFRQ");
								// 机构登记证号(注册号)//jgdjzh
								String zch = enterprise.elementTextTrim("ZCH");
								// 颁发单位
								String pzjgmc = enterprise
										.elementTextTrim("BZJGDM");
								// ****************
								tempDate.setSameId(jgdm);
								tempDate.setCodeId(jgdm);
								tempDate.setOrgName(jgmc);
								tempDate.setOrgType(jglx);
								tempDate.setAreaId(xzqh);
								tempDate.setOrgAddress(jgdz);
								tempDate.setBeginTime(bzrq);
								tempDate.setEndTime(zfrq);
								tempDate.setRegistrationMark(zch);
								tempDate.setAwardedUnit(pzjgmc);
								tempDate.setTempUuid(tempUuid);
								// ****************
								list.add(tempDate);
							}

						}
					}

				} else if ("3".equals(hiddenNumber)) {

					if (!"0".equals(datasize)) {
						while (iterForDatainfo.hasNext()) {
							object = (Element) iterForDatainfo.next();
							iterForEnterprise = object
									.elementIterator("ENTERPRISE");
							while (iterForEnterprise.hasNext()) {
								DwData tempDate = new DwData();
								enterprise = (Element) iterForEnterprise.next();
								// 机构代码
								String jgdm = enterprise
										.elementTextTrim("JGDM");
								// 机构名称
								String jgmc = enterprise
										.elementTextTrim("JGMC");
								// 经济行业
								String jjhy = enterprise
										.elementTextTrim("JJHY");
								// 经济类型
								String jjlx = enterprise
										.elementTextTrim("JJLX");
								// 机构类型
								String jglx = enterprise
										.elementTextTrim("JGLX");
								// 行政区划
								String xzqh = enterprise
										.elementTextTrim("XZQH");
								// 机构地址
								String jgdz = enterprise
										.elementTextTrim("JGDZ");
								// 办证日期
								String bzrq = enterprise
										.elementTextTrim("BZRQ");
								// 作废日期
								String zfrq = enterprise
										.elementTextTrim("ZFRQ");
								// 机构登记证号(注册号)//jgdjzh
								String zch = enterprise.elementTextTrim("ZCH");
								// 颁发单位
								String pzjgmc = enterprise
										.elementTextTrim("BZJGDM");
								// ****************
								tempDate.setSameId(jgdm);
								tempDate.setCodeId(jgdm);
								tempDate.setOrgName(jgmc);
								tempDate.setOrgType(jglx);
								tempDate.setAreaId(xzqh);
								tempDate.setOrgAddress(jgdz);
								tempDate.setBeginTime(bzrq);
								tempDate.setEndTime(zfrq);
								tempDate.setRegistrationMark(zch);
								tempDate.setAwardedUnit(pzjgmc);
								tempDate.setTempUuid(tempUuid);
								// ****************
								list.add(tempDate);
							}

						}
					}

				}

				tempResult.getResult().put("list", list);
				tempResult.setCheckResult("1");
			}

		} catch (Exception e) {
			e.printStackTrace();
			tempResult.setCheckResultAndErrorInfo("3", "后台异常,code:Common-04");

		}
	}

	public static void putPropertyValueByNameToMap(Map<String, String> hashmap,
			Element property, String names[]) {
		int length = names.length;
		String name = property.attributeValue("name");
		for (int i = 0; i < length; i++) {
			if (names[i].equals(name)) {
				hashmap.put(name, property.attributeValue("value"));
				break;
			}
		}
	}

	public static String getRigthWordForXml(String word) {

		String tempWord = null;
		if (word != null) {
			char[] tempChar = word.toCharArray();
			StringBuffer tempString = new StringBuffer();
			for (char x : tempChar) {
				if ("&".equals(x)) {
					tempString.append("&amp;");
				} else if ("<".equals(x)) {
					tempString.append("&lt;");
				} else if (">".equals(x)) {
					tempString.append("&gt;");
				} else if ("\'".equals(x)) {
					tempString.append("&apos;");
				} else if ("\"".equals(x)) {
					tempString.append("&quot;");
				} else {
					tempString.append(x);
				}
			}
			tempWord = tempString.toString();
			// tempXml=xml.replaceAll("&", "&amp;");
			// tempXml=tempXml.replaceAll("<", "&lt;");
			// tempXml=tempXml.replaceAll(">", "&gt;");
			// tempXml=tempXml.replaceAll("\'", "&apos;");
			// tempXml=tempXml.replaceAll("\"", "&quot;");
		}
		return tempWord;
	}

	public static String doCodeService(String xmlFrom)
			throws NoSuchAlgorithmException, KeyManagementException {

		String returnXml = null;
		String soapUrl = Common.getValueFromProperties("xml_service_url");

		TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
			public java.security.cert.X509Certificate[] getAcceptedIssuers() {
				return null;
			}

			public void checkClientTrusted(
					java.security.cert.X509Certificate[] certs, String authType) {
			}

			public void checkServerTrusted(
					java.security.cert.X509Certificate[] certs, String authType) {
			}
		} };
		HostnameVerifier dummyHostnameVerifier = new HostnameVerifier() {
			public boolean verify(String hostname, SSLSession session) {
				return true;
			}
		};

		SSLContext sc = SSLContext.getInstance("SSL");
		sc.init(null, trustAllCerts, new SecureRandom());
		HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
		HttpsURLConnection.setDefaultHostnameVerifier(dummyHostnameVerifier);
		try {
			Client client = new Client(new URL(soapUrl));
			Object[] results = client.invoke(
					Common.getValueFromProperties("xml_service_methodName"),
					new Object[] { xmlFrom });
			returnXml = results[0].toString();
			if(client!=null){
				client.close();
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return returnXml;
	}

	public static String checkCodeId(String codeId) {

		String result = null;
		int checkNumber = 0;
		if (codeId == null) {
			result = "组织机构代码号不能为空";
		} else if (codeId.length() != 9) {
			result = "请输入9位组织机构代码号";
		} else {
			// 验证
			int w[] = { 3, 7, 9, 10, 5, 8, 4, 2 };
			int zz = 0;
			int z = 0;
			char temp;
			codeId = codeId.toUpperCase();
			for (int i = 0; i < 8; i++) {
				temp = codeId.charAt(i);
				if (temp >= 'A' && temp <= 'Z') {
					z = (temp - 55) * w[i];
				} else if (temp >= '0' && temp <= '9') {
					z = (temp - 48) * w[i];
				} else {
					result = "机构代码不正确";
					break;
				}
				zz = zz + z;
			}
			if (result == null) {
				checkNumber = 11 - (zz % 11);
				if (checkNumber == 10) {
					if ('X' != codeId.charAt(8)) {
						result = "机构代码不正确";
					}
				} else if (checkNumber == 11) {
					if ('0' != codeId.charAt(8)) {
						result = "机构代码不正确";
					}
				} else {
					if (checkNumber + 48 != codeId.charAt(8)) {
						result = "机构代码不正确";
					}
				}
			}
		}
		// 如果result为null，是正确的
		return result;
	}

	public static void main(String[] args) {
		HashMap<String, String> values = new HashMap<String, String>();
		values.put("USERNAME", "aaa");
		values.put("CODE", "bbb");
		String xml = Common.changeToXml("xmlFrom1", values);
		System.out.println(xml);
	}*/

}
