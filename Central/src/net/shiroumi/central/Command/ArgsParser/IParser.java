package net.shiroumi.central.Command.ArgsParser;

import java.util.Deque;
import java.util.List;

public interface IParser<T> {
	public boolean accept  (String par1Arg);
	public void    process (Deque<String> par1Args);
	public List<T> getValue();

}
