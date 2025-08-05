Pod::Spec.new do |spec|
  spec.name          = 'BingoSDK'
  spec.version       = '1.0.0'
  spec.homepage      = 'https://github.com/Asutosh11/Bingo'
  spec.source        = { :git => 'https://github.com/Asutosh11/Bingo.git', :tag => "#{spec.version}" }
  spec.authors       = { 'Your Name' => 'panda.asutosh04@gmail.com' }
  spec.license       = { :type => 'MIT', :file => 'LICENSE' }
  spec.summary       = 'Kotlin Multiplatform library for address management with API integration'
  spec.description   = 'BingoSDK provides address management functionality with API integration for iOS and Android applications.'

  spec.ios.deployment_target = '12.0'
  
  spec.vendored_frameworks = 'shared/build/bin/iosArm64/releaseFramework/BingoSDK.framework'
  
  spec.pod_target_xcconfig = {
    'KOTLIN_PROJECT_PATH' => ':shared',
    'PRODUCT_MODULE_NAME' => 'BingoSDK',
  }

  spec.script_phases = [
    {
      :name => 'Build BingoSDK',
      :execution_position => :before_compile,
      :shell_path => '/bin/sh',
      :script => <<-SCRIPT
        if [ "YES" = "$OVERRIDE_KOTLIN_BUILD_IDE_SUPPORTED" ]; then
          echo "Skipping Gradle build task invocation due to OVERRIDE_KOTLIN_BUILD_IDE_SUPPORTED environment variable set to \"YES\""
          exit 0
        fi
        set -ev
        REPO_ROOT="$PODS_TARGET_SRCROOT"
        "$REPO_ROOT/gradlew" -p "$REPO_ROOT" $KOTLIN_PROJECT_PATH:syncFramework \
            -Pkotlin.native.cocoapods.platform=$PLATFORM_NAME \
            -Pkotlin.native.cocoapods.archs="$ARCHS" \
            -Pkotlin.native.cocoapods.configuration="$CONFIGURATION"
      SCRIPT
    }
  ]
end
