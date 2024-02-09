package com.sofa.cinema.exports;

import com.sofa.cinema.Order;
import com.sofa.cinema.errors.ExportException;

public interface ExportBehaviour {
    void export(Order order) throws ExportException;
}
