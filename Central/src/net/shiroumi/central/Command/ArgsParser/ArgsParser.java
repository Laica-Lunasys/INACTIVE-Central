package net.shiroumi.central.Command.ArgsParser;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class ArgsParser {

	private final List<ICommandParser<Entry<String, List<?>>>> parserList = new ArrayList<>();

	public void addCommandParser(ICommandParser<Entry<String, List<?>>> par1Parser) {
		parserList.add(par1Parser);
	}

	public void parse(String par1Args) {
		Deque<String> args = new ArrayDeque<String>(Arrays.asList(par1Args.split("\\s")));
		for (int i = 0; i < args.size(); i++) {
			String s = args.pop();
			LOOP_END: for (IParser<?> t : parserList) {
				if (s != null && t.accept(s)){
					t.process(args);
					break LOOP_END;
				}
			}
		}
	}

	public Map<String, List<Entry<String, List<?>>>> getValue() {
		Map<String, List<Entry<String, List<?>>>> ret = new HashMap<>();
		for(ICommandParser<Entry<String, List<?>>> t : parserList) {
			List<Entry<String, List<?>>> valList = new ArrayList<>();
			for(Entry<String,List<?>> entry : t.getValue()) {
				valList.add(entry);
			}
			ret.put(t.getCommand(), valList);
		}
		return ret;
	}

}
