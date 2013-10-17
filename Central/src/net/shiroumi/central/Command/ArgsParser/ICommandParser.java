package net.shiroumi.central.Command.ArgsParser;

public interface ICommandParser<T> extends IParser<T> {
	public String getCommand();
	public <E> void addOption (String par1ShortArg,
			 String par2LongArg,
			 int par3OptionAmount,
			 IConverter<E> par4Conv);
	public <E> void addOption (ParseOption<E> par1Option);

}
