/*
Since XML is very verbose, you are given a way of encoding it where each tag gets mapped to a pre-defined integer 
value. The language/grammar is as follows:
	Element 	--> Tag Attributes END Children END 
	Attribute 	--> Tag Value
	END 		--> 0
	Tag 		--> some predefined mapping to int 
	Value 		--> string value END
For example, the following XML might be converted into the compressed string below 
(assuming a mapping of family -> 1, person -> 2, frstName -> 3, lastName -> 4, state -> 5).
	<family lastName="McDowell" state="CA">
		<person firstName="Gayle">Some Message</person>
	</family>
Becomes:
	1 4 McDowell 5 CA 0 2 3 Gayle 0 Some Message 0 0
Write code to print the encoded version of an XML element (passed in E Lament and Attribute objects).
*/

import java.util.ArrayList;

class Attribute {
	public String tag;
	public String value;
	public Attribute(String t, String v) {
		tag = t;
		value = v;
	}

	public String getTagCode() {
		if (tag == "family") {
			return "1";
		} else if (tag == "person") {
			return "2";
		} else if (tag == "firstName") {
			return "3";
		} else if (tag == "lastName") {
			return "4";
		} else if (tag == "state") {
			return "5";
		} 
		return "--";
	}
}

class Element {
	public ArrayList<Attribute> attributes;
	public ArrayList<Element> children;
	public String name;
	public String value;

	public Element(String n) {
		name = n;
		attributes = new ArrayList<Attribute>();
		children = new ArrayList<Element>();
	}

	public Element(String n, String v) {
		name = n;
		value = v;
		attributes = new ArrayList<Attribute>();
		children = new ArrayList<Element>();
	}	

	public String getNameCode() {
		if (name == "family") {
			return "1";
		} else if (name == "person") {
			return "2";
		} else if (name == "firstName") {
			return "3";
		} else if (name == "lastName") {
			return "4";
		} else if (name == "state") {
			return "5";
		} 
		return "--";
	}

	public void insert(Attribute attribute) {
		attributes.add(attribute);
	}

	public void insert(Element child) {
		children.add(child);
	}
}

class Solution {
	public static void encode(String v, StringBuffer sb) {
		v = v.replace("0", "\\0");
		sb.append(v);
		sb.append(" ");
	}

	public static void encodeEnd(StringBuffer sb) {
		sb.append("0");
		sb.append(" ");
	}

	public static void encode(Attribute attr, StringBuffer sb) {
		encode(attr.getTagCode(), sb);
		encode(attr.value, sb);
	}

	public static void encode(Element root, StringBuffer sb) {
		encode(root.getNameCode(), sb);
		for (Attribute a : root.attributes) {
			encode(a, sb);
		}
		encodeEnd(sb);
		if (root.value != null && root.value != "") {
			encode(root.value, sb);
		} else {
			for (Element e : root.children) {
				encode(e, sb);
			}
		}
		encodeEnd(sb);
	}

	public static String encodeToString(Element root) {
		StringBuffer sb = new StringBuffer();
		encode(root, sb);
		return sb.toString();
	}

	public static void main(String args[]) {
		Element root = new Element("family");
		Attribute a1 = new Attribute("lastName", "0");
		Attribute a2 = new Attribute("state", "CA");
		root.insert(a1);
		root.insert(a2);

		Element child = new Element("person", "Some Message");
		Attribute a3 = new Attribute("firstName", "Gayle");
		child.insert(a3);

		root.insert(child);

		String s = encodeToString(root);
		System.out.println(s);
	}
}