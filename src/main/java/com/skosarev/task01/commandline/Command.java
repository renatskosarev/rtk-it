package com.skosarev.task01.commandline;

import java.sql.SQLException;

// Реализации этого интерфейса отвечают за обработку консольных команд
public interface Command {
    void execute() throws SQLException;

    String getName();
}
