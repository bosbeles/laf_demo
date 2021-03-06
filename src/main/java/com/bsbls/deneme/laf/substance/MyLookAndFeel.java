package com.bsbls.deneme.laf.substance;

import org.pushingpixels.substance.api.*;
import org.pushingpixels.substance.api.colorscheme.SubstanceColorScheme;
import org.pushingpixels.substance.api.skin.GraphiteAquaSkin;

public class MyLookAndFeel extends SubstanceLookAndFeel {


    public MyLookAndFeel() {
        this(new GraphiteAquaSkin());
    }

    /**
     * Creates a new skin-based Substance look-and-feel.
     *
     * @param skin Skin.
     */
    public MyLookAndFeel(SubstanceSkin skin) {
        super(skin);

        initColorSchemes(skin);
    }

    private void initColorSchemes(SubstanceSkin skin) {
        SubstanceSkin.ColorSchemes businessSchemes = SubstanceSkin
                .getColorSchemes(this.getClass().getClassLoader().getResourceAsStream("org/pushingpixels/substance/api/skin/graphite.colorschemes"));

        SubstanceColorScheme activeScheme = businessSchemes.get("Graphite Aqua");
        SubstanceColorScheme enabledScheme = businessSchemes.get("Graphite Enabled");
        SubstanceColorScheme disabledScheme = businessSchemes.get("Graphite Disabled");

        SubstanceColorSchemeBundle bundle = new SubstanceColorSchemeBundle(activeScheme, enabledScheme, disabledScheme);
        MyColorScheme myColorScheme = new MyColorScheme();

        bundle.registerColorScheme(myColorScheme, SubstanceSlices.ColorSchemeAssociationKind.MARK, ComponentState.ENABLED, ComponentState.SELECTED, ComponentState.ROLLOVER_SELECTED, ComponentState.ROLLOVER_ARMED);
        bundle.registerColorScheme(new MyColorScheme2(), SubstanceSlices.ColorSchemeAssociationKind.BORDER, ComponentState.SELECTED);

        skin.registerDecorationAreaSchemeBundle(bundle, SubstanceSlices.DecorationAreaType.NONE, SubstanceSlices.DecorationAreaType.GENERAL);

    }
}
