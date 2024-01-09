package org.dc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Stack;

/**
 * Consider the representation of a filepath as:
 *
 *  dir\n\tsubdir1\n\tsubdir2\n\t\tfile.ext
 *
 * where each new directory level, or depth, is specified with a `\t` character, and each
 * file or dir is delimited by a `\n`.
 *
 *  That is, `\t` in the string signifies the depth level from the root or top-level
 *  directory.
 *
 *  So for the above example:
 *  dir
 *   |-- subdir1
 *   |-- subdir2
 *      |-- file.ext
 *
 */
public class LongestFilepath {
    private static final FileNode ROOT = new FileNode(
            "",
            Optional.empty(),
            null
    );

    public static FileNode parse(String path) {
        return parse(path, new Stack<>());
    }

    private static FileNode parse(
            String path,
            Stack<FileNode> currentStack
    ) {
        // There must always be at least one element denoting the current file
        //
        for (var pathComponent: path.split("\\n")) {
            // Determine the parent directory
            var fileLocator = pathComponent.split("\\t");
            var fileDepth = fileLocator.length - 1;

            // find corresponding parent based on current depth
            var popCount = currentStack.size() - fileDepth;
            for (int i = 0; i < popCount; i++) {
                currentStack.pop();
            }

            FileNode currentFile;
            if (currentStack.empty()) {
                currentFile = parseFilename(fileLocator[fileLocator.length - 1], LongestFilepath.ROOT);
            } else {
                currentFile = parseFilename(
                        fileLocator[fileLocator.length - 1],
                        currentStack.peek()
                );
                currentStack.peek().children.add(currentFile);
            }
            if (currentFile.extension.isEmpty()) {
                currentStack.push(currentFile);
            }

        }

        return currentStack.firstElement();
    }

    private static FileNode parseFilename(String fileName, FileNode parent) {
        var extensionStartIdx = fileName.lastIndexOf('.');
        var name = (extensionStartIdx == -1)
                ? fileName
                : fileName.substring(0, extensionStartIdx);
        Optional<String> extension = (extensionStartIdx == -1)
                ? Optional.empty()
                : Optional.of(fileName.substring(extensionStartIdx + 1));

        return new FileNode(name, extension, parent);
    }


    static class FileNode {
        String name;
        Optional<String> extension;
        FileNode parent;
        List<FileNode> children = new ArrayList<>();

        public FileNode(
                String name, Optional<String> extension, FileNode parent
        ) {
            this.name = name;
            this.extension = extension;
            this.parent = parent;
        }

        public String getName() {
            var builder = new StringBuilder(this.name);
            this.extension.ifPresent(s -> builder.append(".").append(s));

            return builder.toString();
        }

        @Override
        public String toString() {
            var builder = new StringBuilder(this.getName());
            for (var child : this.children) {
                var parent = child.parent;
                builder.append("\n");
                while (parent != LongestFilepath.ROOT) {
                    builder.append("\t");
                    parent = parent.parent;

                }
                builder.append("| ");
                builder.append(child);
            }

            return builder.toString();
        }

        public List<String> list() {
            var fileName = this.getName();
            var paths = new ArrayList<String>();
            paths.add(fileName);
            for (var child : this.children) {
                for (var relPath : child.list()) {
                    paths.add(fileName + "/" + relPath);
                }
            }
            return paths;
        }

        public String longestFilepath() {
            return this.list().stream().reduce(
                    "",
                    (l, r) -> l.length() > r.length() ? l : r
            );
        }
    }
}
