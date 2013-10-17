package net.shiroumi.central.Command.ArgsParser;

import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class ParseOption<T> implements IParser<T> {

	private final String        shortArg,
						        longArg;
	private final int           argc;
	private final IConverter<T> conv;
	private List<String>        vals = new ArrayList<String>();

	public ParseOption(String par1ShortOption,
					   String par2LongOption,
					   int par3OptionAmount,
					   IConverter<T> par4Converter) {
		shortArg = par1ShortOption;
		longArg  = par2LongOption;
		argc     = par3OptionAmount;
		conv     = par4Converter;
	}

	@Override
	public boolean accept(String par1Arg) {
		return (("-" + shortArg).equals(par1Arg)) || 
			   (("--" + longArg).equals(par1Arg));
	}

	@Override
	public void process(Deque<String> par1Args) {
		for(int i = 0; i < argc; i++){
			String s = par1Args.poll();
			if(s.length() < 1) continue;
			if(s != null && conv.accept(s)){
				vals.add(s);
			} else {
				throw new IllegalArgumentException();
			}
		}
	}

	@Override
	public List<T> getValue() {
		List<T> ret = new ArrayList<T>();
		for(String s : vals) ret.add(conv.convert(s));
		return ret;
	}

	public String getShortOption() {
		return shortArg;
	}

	public String getLongOption() {
		return longArg;
	}
}
