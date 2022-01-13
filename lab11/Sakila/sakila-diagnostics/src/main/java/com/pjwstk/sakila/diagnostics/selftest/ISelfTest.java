package com.pjwstk.sakila.diagnostics.selftest;

import com.pjwstk.sakila.diagnostics.contract.SelftestResult;

public interface ISelfTest {
    public SelftestResult execute();

    String getName();

    String getDescription();
}
