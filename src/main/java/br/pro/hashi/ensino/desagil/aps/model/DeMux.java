package br.pro.hashi.ensino.desagil.aps.model;

public class DeMux extends Gate {
    private final NandGate nandA;
    private final NandGate nandB;
    private final NandGate nandC;
    private final NandGate nandD;
    private final NandGate nandE;

    // Modelo usado: https://upload.wikimedia.org/wikipedia/commons/thumb/5/5b/DEMUX_Gate.svg/512px-DEMUX_Gate.svg.png
    public DeMux() {
        super("DeMux", 2, 2);
        nandA = new NandGate();
        nandB = new NandGate();
        nandC = new NandGate();
        nandD = new NandGate();
        nandE = new NandGate();

        // Conexões Internas
        nandB.connect(1, nandA);

        nandD.connect(0, nandB);
        nandD.connect(1, nandB);

        nandE.connect(0, nandC);
        nandE.connect(1, nandC);
    }

    @Override
    public boolean read(int outputPin) {
        // Conexões de Saída (Output)
        if (outputPin == 0) {
            return nandD.read();
        } else if (outputPin == 1) {
            return nandE.read();
        } else {
            throw new IndexOutOfBoundsException(outputPin);
        }
    }

    @Override
    public void connect(int inputPin, SignalEmitter emitter) {
        switch (inputPin) {
            // Conexões de Entrada (Emitter)
            case 0:
                nandB.connect(0, emitter);
                nandC.connect(1, emitter);
                break;
            case 1:
                nandA.connect(0, emitter);
                nandA.connect(1, emitter);
                nandC.connect(0, emitter);
                break;
            default:
                throw new IndexOutOfBoundsException(inputPin);

        }
    }
}
