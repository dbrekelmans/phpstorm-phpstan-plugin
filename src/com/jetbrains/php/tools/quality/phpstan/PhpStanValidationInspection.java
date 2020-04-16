package com.jetbrains.php.tools.quality.phpstan;

import com.jetbrains.php.lang.inspections.PhpInspection;
import com.jetbrains.php.tools.quality.QualityToolAnnotator;
import com.jetbrains.php.tools.quality.QualityToolValidationInspection;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

import static com.intellij.openapi.util.text.StringUtil.isNotEmpty;
import static com.intellij.openapi.util.text.StringUtil.split;

public class PhpStanValidationInspection extends QualityToolValidationInspection {
  public boolean FULL_PROJECT = false;
  public String OPTIONS = "--memory-limit=2G";
  public int level = 4;
  public String config = "";

  public final static String DISPLAY_NAME = "PHPStan Validation";

  @Override
  public String @NotNull [] getGroupPath() {
    return PhpInspection.GROUP_PATH_GENERAL;
  }

  @NotNull
  @Override
  public String getShortName() {
    return getClass().getSimpleName();
  }

  @Override
  public JComponent createOptionsPanel() {
    final PhpStanOptionsPanel optionsPanel = new PhpStanOptionsPanel(this);
    optionsPanel.init();
    return optionsPanel.getOptionsPanel();
  }

  @NotNull
  @Override
  protected QualityToolAnnotator getAnnotator() {
    return PhpStanAnnotatorProxy.INSTANCE;
  }

  @Override
  public String getToolName() {
    return "PHPStan";
  }

  public List<String> getCommandLineOptions(List<String> filePath) {
    ArrayList<String> options = new ArrayList<>();
    options.add("analyze");
    options.add("--level=" + level);
    if (isNotEmpty(config)) {
      options.add("--configuration=" + config);
    }
    options.addAll(split(OPTIONS, " "));
    options.add("--error-format=checkstyle");
    options.add("--no-progress");
    options.addAll(filePath);
    return options;
  }
}
