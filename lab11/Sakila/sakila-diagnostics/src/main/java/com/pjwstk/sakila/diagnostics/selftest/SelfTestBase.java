package com.pjwstk.sakila.diagnostics.selftest;

import com.pjwstk.sakila.diagnostics.contract.SelftestResult;

public abstract class SelfTestBase implements SelfTest {
    String name;
    String description;

    abstract public SelftestResult execute();
}
