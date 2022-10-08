import 'package:flutter/material.dart';
import 'package:rc_plugin/rc_plugin.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatefulWidget {
  const MyApp({Key? key}) : super(key: key);

  @override
  State<MyApp> createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  @override
  void initState() {
    super.initState();
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: const Text('Plugin example app'),
        ),
        body: Center(
          child: TextButton(
            onPressed: () async {
              final result = await RcPlugin.installApk(path: 'path', applicationName: 'applicationName');
              // ignore: avoid_print
              print(result);
            },
            child: const Text('按钮'),
          ),
        ),
      ),
    );
  }
}
