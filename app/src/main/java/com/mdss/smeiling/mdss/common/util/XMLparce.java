package com.mdss.smeiling.mdss.common.util;

import android.content.Context;

import com.mdss.smeiling.mdss.model.TestCaseBean;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

/**
 * Created by songmeiling on 2016/1/20.
 */
public class XMLparce {

    private Context context;

    public XMLparce(Context context) {
        this.context = context;
    }

    public List<TestCaseBean> readXML() {
        List<TestCaseBean> testCaseBeans = new ArrayList<TestCaseBean>();
        try {
            InputStream inStream = context.getAssets().open("itcast.xml");
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document dom = builder.parse(inStream);
            Element root = dom.getDocumentElement();
            NodeList items = root.getElementsByTagName("testcase");
            for (int i = 0; i < items.getLength(); i++) {
                TestCaseBean testCase = new TestCaseBean();
                Element testcaseNode = (Element) items.item(i);
                testCase.setId(new Integer(testcaseNode.getAttribute("id")));
                NodeList childNodes = testcaseNode.getChildNodes();
                for (int j = 0; j < childNodes.getLength(); j++) {
                    Node node = childNodes.item(j);
                    if (node.getNodeType() == Node.ELEMENT_NODE) {
                        Element childNode = (Element) node;
                        if ("title".equals(childNode.getNodeName())) {
                            testCase.setTitle(childNode.getFirstChild().getNodeValue());
                        } else if ("code".equals(childNode.getNodeName())) {
                            testCase.setCode(new Short(childNode.getFirstChild().getNodeValue()));
                        } else if ("imgid".equals(childNode.getNodeName())) {
                            testCase.setImgId(childNode.getFirstChild().getNodeValue());
                        } else if ("contextid".equals(childNode.getNodeName())) {
                            testCase.setContextId(childNode.getFirstChild().getNodeValue());
                        } else if ("activity".equals(childNode.getNodeName())) {
                            testCase.setTestingAct(childNode.getFirstChild().getNodeValue());
                        }
                    }
                }
                testCaseBeans.add(testCase);
            }
            inStream.close();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return testCaseBeans;
    }

}
