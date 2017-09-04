window.org_vaadin_quagga_QuaggaJsComponent = function() {

    var self = this;

    Quagga.onDetected(function(data) {
        self.onDetected(data.codeResult.code, data.codeResult.format);
    });

    Quagga.init({
        inputStream : {
            name : "Live",
            type : "LiveStream",
            target: self.getElement(),
            constraints: {
                width: 640,
                height: 480,
                facingMode: "environment"
            },
            area: {
                top: "0%",
                right: "0%",
                left: "0%",
                bottom: "0%"
            }
        },
        locator: {
            patchSize: "medium",
            halfSample: true
        },
        numOfWorkers: 2,
        frequency: 10,
        locate: true,
        decoder : {
            readers : self.getState().readers,
            debug: {
                drawBoundingBox: true,
                showFrequency: false,
                drawScanline: true,
                showPattern: false
            },
            multiple: false
        },
        debug: true
    },
        function(err) {
            if (err) {
                console.log(err);
            }
        }
    );

    self.start = function () {
        Quagga.start();
    };

    self.stop = function () {
        Quagga.stop();
    }
};

