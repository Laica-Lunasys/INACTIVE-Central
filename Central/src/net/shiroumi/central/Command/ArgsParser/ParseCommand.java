package net.shiroumi.central.Command.ArgsParser;

import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Map.Entry;

public class ParseCommand implements ICommandParser<Entry<String, List<?>>> {

	private List<ParseOption<?>> optionList = new ArrayList<>();
	private final String command;

	public ParseCommand(String par1CommandName) {
		command = par1CommandName;
	}

	@Override
	public <E> void addOption(String par1ShortArg, String par2LongArg,
			int par3OptionAmount, IConverter<E> par4Conv) {
		this.addOption(
				new ParseOption<E>(par1ShortArg,
								   par2LongArg,
								   par3OptionAmount,
								   par4Conv));
	}

	@Override
	public <E> void addOption(ParseOption<E> par1Option) {
		optionList.add(par1Option);
	}

	@Override
	public boolean accept(String par1Arg) {
		return command.equalsIgnoreCase(par1Arg);
	}

	@Override
	public void process(Deque<String> par1Args) {
		String s;
		for(;;) {
			if((s = par1Args.poll()) == null) return;
			for(ParseOption<?> t : optionList)
				if(t.accept(s)) t.process(par1Args);
		}
	}

	@Override
	public List<Entry<String, List<?>>> getValue() {
		List<Entry<String, List<?>>> ret = new ArrayList<>();
		for(ParseOption<?> p : optionList) {
			ret.add(new ArgsEntry<String, List<?>>(p.getShortOption(), p.getValue()));
		}
		return ret;
	}

	@Override
	public String getCommand() {
		return command;
	}

}
