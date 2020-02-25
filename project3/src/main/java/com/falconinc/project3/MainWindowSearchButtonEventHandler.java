/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.falconinc.project3;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.naming.OperationNotSupportedException;

/**
 *
 * @author epigott
 */
public class MainWindowSearchButtonEventHandler implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            throw new OperationNotSupportedException("Non-implemented button!");
        }
        catch (OperationNotSupportedException o){
            System.out.println("The Search Button does not have an action assigned to it yet!");
        }
    }
}
