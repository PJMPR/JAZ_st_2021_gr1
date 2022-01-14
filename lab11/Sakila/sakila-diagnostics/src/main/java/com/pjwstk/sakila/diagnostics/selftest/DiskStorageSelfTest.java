package com.pjwstk.sakila.diagnostics.selftest;

import com.pjwstk.sakila.diagnostics.contract.SelftestResult;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Component
public class DiskStorageSelfTest extends SelfTestBase{

    public DiskStorageSelfTest() {
        name = "DiskStorageSelfTest";
        description = "Checks if> = 5% free disk space remains";
    }

    @Override
    public SelftestResult execute() {
        List<String> errors = new ArrayList<>();
        SelftestResult selftestResult = new SelftestResult(name, description, false, errors);

        if(getUsableDiscSpacePercentage() >= 5) selftestResult.setPassed(true);
        else {
            selftestResult.setPassed(false);
            errors.add("not enough space");
        }

        return selftestResult;
    }

    public long getUsableDiscSpacePercentage(){
        File file = new File("c:");
        return (file.getUsableSpace() / file.getTotalSpace()) * 100;
    }
}
