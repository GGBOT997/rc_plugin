#import "RcPlugin.h"
#if __has_include(<rc_plugin/rc_plugin-Swift.h>)
#import <rc_plugin/rc_plugin-Swift.h>
#else
// Support project import fallback if the generated compatibility header
// is not copied when this plugin is created as a library.
// https://forums.swift.org/t/swift-static-libraries-dont-copy-generated-objective-c-header/19816
#import "rc_plugin-Swift.h"
#endif

@implementation RcPlugin
+ (void)registerWithRegistrar:(NSObject<FlutterPluginRegistrar>*)registrar {
  [SwiftRcPlugin registerWithRegistrar:registrar];
}
@end
