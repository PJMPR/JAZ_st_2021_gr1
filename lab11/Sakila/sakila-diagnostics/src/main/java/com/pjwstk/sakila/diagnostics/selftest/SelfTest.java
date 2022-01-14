package com.pjwstk.sakila.diagnostics.selftest;

import com.pjwstk.sakila.diagnostics.contract.SelftestResult;

public interface SelfTest {
    public SelftestResult execute();
}
