package net.shiroumi.central.Command.ArgsParser;

public final class Converters {
	public static IConverter<String> getStringConverter() {
		return new IConverter<String>() {
			@Override
			public boolean accept(String par1Arg) {
				return true;
			}

			@Override
			public String convert(String par1Arg) {
				return par1Arg;
			}
		};
	}

	public static IConverter<Integer> getIntegerConverter() {
		return new IConverter<Integer>() {
			@Override
			public boolean accept(String par1Arg) {
				try {
					Integer.valueOf(par1Arg);
				} catch(NumberFormatException e) {
					return false;
				}
				return true;
			}

			@Override
			public Integer convert(String par1Arg) {
				return Integer.valueOf(par1Arg);
			}
		};
	}

	public static IConverter<Double> getDoubleConverter() {
		return new IConverter<Double>() {
			@Override
			public boolean accept(String par1Arg) {
				try {
					Double.valueOf(par1Arg);
				} catch(NumberFormatException e) {
					return false;
				}
				return true;
			}

			@Override
			public Double convert(String par1Arg) {
				return Double.valueOf(par1Arg);
			}
		};
	}
}
