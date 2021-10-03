package com.weaveown.design.behavioral.memento;

import java.util.Scanner;
import java.util.Stack;

/**
 * @author wangwei
 * @date 2021/9/26
 */
public class Demo {
    static class Snapshot {
        private String text;

        public Snapshot(String text) {
            this.text = text;
        }

        public String getText() {
            return this.text;
        }


    }

    static class InputText {
        private StringBuilder text = new StringBuilder();

        public String getText() {
            return text.toString();
        }

        public void append(String text) {
            this.text.append(text);
        }

        public Snapshot createSnapshot() {
            return new Snapshot(this.getText());
        }

        public void restore(Snapshot snapshot) {
            this.text.replace(0, this.text.length(), snapshot.getText());
        }

        public String toString() {
            return this.text.toString();
        }
    }

    static class SnapshotHolder {
        private Stack<Snapshot> snapshots = new Stack<>();

        public Snapshot popSnapshot() {
            return snapshots.pop();
        }

        public void pushSnapshot(Snapshot snapshot) {
            snapshots.push(snapshot);
        }
    }

    public static void main(String[] args) {
        InputText inputText = new InputText();
        SnapshotHolder snapshotsHolder = new SnapshotHolder();
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String input = scanner.next();
            if (input.equals(":list")) {
                System.out.println(inputText.toString());
            } else if (input.equals(":undo")) {
                Snapshot snapshot = snapshotsHolder.popSnapshot();
                inputText.restore(snapshot);
            } else {
                snapshotsHolder.pushSnapshot(inputText.createSnapshot());
                inputText.append(input);
            }
        }
    }

}
