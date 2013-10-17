package net.shiroumi.central.Command.ArgsParser;

public interface IConverter<E> {
	public boolean accept(String par1Arg);
	public E      convert(String par1Arg);
}
