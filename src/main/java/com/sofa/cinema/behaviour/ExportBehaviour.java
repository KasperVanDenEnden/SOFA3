package com.sofa.cinema.behaviour;

import com.sofa.cinema.Order;
import com.sofa.cinema.errors.ExportException;

public interface ExportBehaviour {
    public void export(Order order) throws ExportException;
}
