/*
* @overview: 
* @Author: rcc 
* @Date: 2022-05-05 11:01:55 
*/

import 'dart:io';
import 'dart:async';

import 'package:flutter/services.dart';

class RcPlugin {
  static const MethodChannel _channel = MethodChannel('rc_plugin');

  /// 安装Apk
  ///
  /// [path] 文件路径
  /// [applicationName] 应用名
  static Future<bool> installApk({
    required String path,
    required String applicationName,
  }) async {
    assert(Platform.isAndroid);

    try {
      final Map<String, String> arguments = {
        'path': path,
        'applicationName': applicationName,
      };

      final String result = await _channel.invokeMethod('install_apk', arguments);

      return result == '1';
    } catch (_) {
      return false;
    }
  }
}
