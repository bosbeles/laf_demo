package com.bsbls.deneme.laf.substance;

import org.pushingpixels.substance.api.colorscheme.SchemeDerivedColors;
import org.pushingpixels.substance.api.colorscheme.SubstanceColorScheme;
import org.pushingpixels.substance.internal.colorscheme.*;
import org.pushingpixels.substance.internal.utils.SubstanceColorUtilities;

import java.awt.*;

public class MyColorScheme2 implements SubstanceColorScheme {

    /**
     * The main ultra-light color.
     */
    private static final Color mainUltraLightColor = new Color(46, 22, 124);

    /**
     * The main extra-light color.
     */
    private static final Color mainExtraLightColor = new Color(33, 19, 113);

    /**
     * The main light color.
     */
    private static final Color mainLightColor = new Color(31, 17, 104);

    /**
     * The main medium color.
     */
    private static final Color mainMidColor = new Color(47, 6, 53);

    /**
     * The main dark color.
     */
    private static final Color mainDarkColor = new Color(135, 175, 218);

    /**
     * The main ultra-dark color.
     */
    private static final Color mainUltraDarkColor = new Color(2, 1, 23);

    /**
     * The foreground color.
     */
    private static final Color foregroundColor = Color.white;

    /**
     * Indicates whether this color scheme is dark.
     */
    protected boolean isDark ;

    /**
     * Display name of this color scheme.
     */
    protected String displayName;

    /**
     * Resolver for the derived colors.
     */
    private SchemeDerivedColors derivedColorsResolver;

    /**
     * Constructs the basic functionality of a color scheme.
     *
     */
    public MyColorScheme2() {
        this.displayName = "My";
        this.isDark = true;
        this.derivedColorsResolver = new DerivedColorsResolverDark(this);

    }

    @Override
    public Color getForegroundColor() {
        return foregroundColor;
    }

    @Override
    public Color getUltraLightColor() {
        return mainUltraLightColor;
    }

    @Override
    public Color getExtraLightColor() {
        return mainExtraLightColor;
    }

    @Override
    public Color getLightColor() {
        return mainLightColor;
    }

    @Override
    public Color getMidColor() {
        return mainMidColor;
    }

    @Override
    public Color getDarkColor() {
        return mainDarkColor;
    }

    @Override
    public Color getUltraDarkColor() {
        return mainUltraDarkColor;
    }


    @Override
    public final String getDisplayName() {
        return this.displayName;
    }

    @Override
    public final boolean isDark() {
        return isDark;
    }

    @Override
    public final SubstanceColorScheme shift(Color backgroundShiftColor,
                                            double backgroundShiftFactor, Color foregroundShiftColor,
                                            double foregroundShiftFactor) {
        return new ShiftColorScheme(this, backgroundShiftColor,
                backgroundShiftFactor, foregroundShiftColor,
                foregroundShiftFactor, true);
    }

    @Override
    public final SubstanceColorScheme shiftBackground(
            Color backgroundShiftColor, double backgroundShiftFactor) {
        return this.shift(backgroundShiftColor, backgroundShiftFactor, null, 0.0);
    }

    @Override
    public SubstanceColorScheme tint(double tintFactor) {
        return new TintColorScheme(this, tintFactor);
    }

    @Override
    public SubstanceColorScheme tone(double toneFactor) {
        return new ToneColorScheme(this, toneFactor);
    }

    @Override
    public SubstanceColorScheme shade(double shadeFactor) {
        return new ShadeColorScheme(this, shadeFactor);
    }

    @Override
    public SubstanceColorScheme saturate(double saturateFactor) {
        return new SaturatedColorScheme(this, saturateFactor);
    }

    @Override
    public SubstanceColorScheme invert() {
        return new InvertedColorScheme(this);
    }

    @Override
    public SubstanceColorScheme negate() {
        return new NegatedColorScheme(this);
    }

    @Override
    public SubstanceColorScheme hueShift(double hueShiftFactor) {
        return new HueShiftColorScheme(this, hueShiftFactor);
    }

    @Override
    public SubstanceColorScheme blendWith(SubstanceColorScheme otherScheme,
                                          double likenessToThisScheme) {
        return new BlendBiColorScheme(this, otherScheme, likenessToThisScheme);
    }

    @Override
    public final Color getBackgroundFillColor() {
        return derivedColorsResolver.getBackgroundFillColor();
    }

    @Override
    public final Color getFocusRingColor() {
        return derivedColorsResolver.getFocusRingColor();
    }

    @Override
    public final Color getLineColor() {
        return derivedColorsResolver.getLineColor();
    }

    @Override
    public final Color getSelectionForegroundColor() {
        return derivedColorsResolver.getSelectionForegroundColor();
    }

    @Override
    public final Color getSelectionBackgroundColor() {
        return derivedColorsResolver.getSelectionBackgroundColor();
    }

    @Override
    public final Color getWatermarkDarkColor() {
        return derivedColorsResolver.getWatermarkDarkColor();
    }

    @Override
    public final Color getWatermarkLightColor() {
        return derivedColorsResolver.getWatermarkLightColor();
    }

    @Override
    public final Color getWatermarkStampColor() {
        return derivedColorsResolver.getWatermarkStampColor();
    }

    @Override
    public final Color getTextBackgroundFillColor() {
        return derivedColorsResolver.getTextBackgroundFillColor();
    }

    @Override
    public final SubstanceColorScheme named(String colorSchemeDisplayName) {
        this.displayName = colorSchemeDisplayName;
        return this;
    }

    @Override
    public String toString() {
        return this.getDisplayName() + " {\n    kind="
                + (this.isDark() ? "Dark" : "Light") + "\n    colorUltraLight="
                + SubstanceColorUtilities.encode(this.getUltraLightColor())
                + "\n    colorExtraLight="
                + SubstanceColorUtilities.encode(this.getExtraLightColor())
                + "\n    colorLight="
                + SubstanceColorUtilities.encode(this.getLightColor())
                + "\n    colorMid="
                + SubstanceColorUtilities.encode(this.getMidColor())
                + "\n    colorDark="
                + SubstanceColorUtilities.encode(this.getDarkColor())
                + "\n    colorUltraDark="
                + SubstanceColorUtilities.encode(this.getUltraDarkColor())
                + "\n    colorForeground="
                + SubstanceColorUtilities.encode(this.getForegroundColor())
                + "\n}";
    }


    private static class DerivedColorsResolverDark implements SchemeDerivedColors {
        /**
         * The original color scheme.
         */
        SubstanceColorScheme scheme;

        /**
         * Creates the resolver of derived colors for the specified dark color
         * scheme.
         *
         * @param scheme The original color scheme.
         */
        public DerivedColorsResolverDark(SubstanceColorScheme scheme) {
            if (!scheme.isDark()) {
                throw new IllegalArgumentException(
                        "The scheme must be dark: " + scheme.getDisplayName());
            }
            this.scheme = scheme;
        }

        @Override
        public Color getWatermarkStampColor() {
            return SubstanceColorUtilities.getAlphaColor(this.scheme.getUltraLightColor(), 30);
        }

        @Override
        public Color getWatermarkDarkColor() {
            return this.scheme.getLightColor();
        }

        @Override
        public Color getWatermarkLightColor() {
            return this.scheme.getUltraLightColor();
        }

        @Override
        public Color getLineColor() {
            return this.scheme.getMidColor();
        }

        @Override
        public Color getSelectionForegroundColor() {
            return SubstanceColorUtilities.deriveByBrightness(this.scheme.getUltraDarkColor(), -0.5f);
        }

        @Override
        public Color getSelectionBackgroundColor() {
            return SubstanceColorUtilities.deriveByBrightness(this.scheme.getUltraLightColor(), 0.2f);
        }

        @Override
        public Color getBackgroundFillColor() {
            return this.scheme.getMidColor();
        }

        @Override
        public Color getFocusRingColor() {
            return Color.RED;
            // return SubstanceColorUtilities.getAlphaColor(this.scheme.getForegroundColor(), 192);
        }

        @Override
        public Color getTextBackgroundFillColor() {
            return SubstanceColorUtilities.getInterpolatedColor(this.scheme.getMidColor(),
                    this.scheme.getLightColor(), 0.4f);
        }
    }

}


