package com.jetbrains.php.psalm.quality.tools;

import com.intellij.openapi.util.SystemInfo;
import com.jetbrains.php.config.composer.QualityToolsComposerConfigTest;
import com.jetbrains.php.tools.quality.QualityToolConfigurationManager;
import com.jetbrains.php.tools.quality.phpstan.PhpStanConfigurationManager;
import org.jetbrains.annotations.NotNull;

public class PhpStanComposerConfigTest extends QualityToolsComposerConfigTest {

  @Override
  protected QualityToolConfigurationManager getQualityToolConfigurationManager() {
    return PhpStanConfigurationManager.getInstance(getProject());
  }

  @NotNull
  @Override
  protected String getPath() {
    return "bin/phpstan" + (SystemInfo.isWindows ? ".bat" : "");
  }

  @NotNull
  @Override
  protected String getPackageName() {
    return "phpstan/phpstan";
  }
}
