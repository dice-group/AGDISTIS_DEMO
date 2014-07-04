var t = '';
Selector = {};
Selector.getSelected = function() {
    if (window.getSelection) {
        t = window.getSelection();
    } else if (document.getSelection) {
        t = document.getSelection();
    } else if (document.selection) {
        t = document.selection.createRange().text;
    }
    return t;
};

Selector.getSelectionCharOffsetsWithin = function() {
    var start = 0,
        end = 0;
    var sel, range, priorRange;
    var element = document.getElementById("sidebar-content");
    if (typeof window.getSelection != "undefined") {
        console.log(window.getSelection())
        range = window.getSelection().getRangeAt(0);
        priorRange = range.cloneRange();
        priorRange.selectNodeContents(element);
        priorRange.setEnd(range.startContainer, range.startOffset);
        start = priorRange.toString().length;
        end = start + range.toString().length;
    } else if (typeof document.selection != "undefined" && (sel = document.selection).type != "Control") {
        range = sel.createRange();
        priorRange = document.body.createTextRange();
        priorRange.moveToElementText(element);
        priorRange.setEndPoint("EndToStart", range);
        start = priorRange.text.length;
        end = start + range.text.length;
    }
    return {
        start: start,
        end: end
    };
};

Selector.mouseup = function() {
    var st = Selector.getSelected();
    var selection = Selector.getSelectionCharOffsetsWithin();
    if (st != '') {
        var containerId = encodeURIComponent(st + '');
        var $container = $('<div id="' + containerId + '"></div>');
        $('#right-sidebar .innerContainer').append($container);

        $container.append([$('<button>remove</button>')
            .click(function() {
                console.log("remove")
                $container.remove();
            }),
            $('<input type="hidden" ' + 'value="newLabel:' + st + '//' + selection.start + '//' + selection.end + '" ' + 'name="newLabel:' + st + '//' + selection.start + '//' + selection.end + '"/>'),
            $('<span>' + st + '</span><br />')
        ]);
    }
};

var candidateNames = [];
$(document).ready(
    function() {
        $("#newEntity").bind("click", Selector.mouseup);
    });