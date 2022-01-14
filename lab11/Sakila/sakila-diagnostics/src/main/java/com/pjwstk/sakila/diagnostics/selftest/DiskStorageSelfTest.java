package com.pjwstk.sakila.diagnostics.selftest;

import org.springframework.stereotype.Component;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.pjwstk.sakila.diagnostics.contract.SelftestResult;

@Component
public class DiskStorageSelfTest implements ISelfTest {
    private final int PRECENTAGE = 5;

    @Override
    public SelftestResult execute() {
        List<String> errors = new ArrayList<>();
        SelftestResult selftestResult = new SelftestResult(getName(), getDescription(), false, errors);

        if(getUsableDiscSpacePercentage() >= PRECENTAGE) selftestResult.setPassed(true);
        else {
            selftestResult.setPassed(false);
            errors.add(String.format("Not enough space, space left: %x", getUsableDiscSpacePercentage()));
        }

        return selftestResult;
    }

    public long getUsableDiscSpacePercentage(){
        File file = new File(System.getProperty("user.dir"));
        return (file.getUsableSpace() / file.getTotalSpace()) * 100;
    }

    @Override
    public String getName() {
        return "DiskStorageSelfTest";
    }

    @Override
    public String getDescription() {
        return String.format("Checks minimum free space (>=%d%)",PRECENTAGE);
    }
}
