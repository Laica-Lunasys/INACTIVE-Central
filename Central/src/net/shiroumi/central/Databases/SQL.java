package net.shiroumi.central.Databases;

public final class SQL {
	public static final String CREATE_TABLE_BLOCK_DATA = "create table if not exists blocks ( x int, y int, z int, action int, player int );"; 

	public static final int ACTION_BREAK = 0;
	public static final int ACTION_PLACE = 1;
}
