package io.papermc.generator.rewriter.types.simple;

import io.papermc.generator.rewriter.types.Types;
import io.papermc.generator.utils.BlockStateMapping;
import io.papermc.typewriter.replace.SearchMetadata;
import io.papermc.typewriter.replace.SearchReplaceRewriter;
import java.util.Comparator;

public class CraftBlockDataMapping extends SearchReplaceRewriter {

    @Override
    protected void insert(SearchMetadata metadata, StringBuilder builder) {
        BlockStateMapping.MAPPING.entrySet().stream().sorted(Comparator.comparing(entry -> entry.getKey().getCanonicalName())).forEach(entry -> {
            builder.append(metadata.indent());
            builder.append("register(%s.class, %s.data.%s::new);".formatted(entry.getKey().getCanonicalName(), Types.CRAFT_BLOCK_DATA.packageName(), entry.getValue().implName()));
            builder.append('\n');
        });
    }
}
